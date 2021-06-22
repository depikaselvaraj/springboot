package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
import com.example.demo.model.UserPage;
import com.example.demo.model.UserSearchCriteria;

@Repository 
public class UserCriteriaRepository {
	private final EntityManager entitymanager;
	private final CriteriaBuilder criteriabuilder;
	public UserCriteriaRepository(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
		this.criteriabuilder=entitymanager.getCriteriaBuilder();
		
		// TODO Auto-generated constructor stub
	}
	public Page<User>findAllfilters(UserPage userpage,UserSearchCriteria searchcriteria){
		CriteriaQuery<User> criteriaquery=criteriabuilder.createQuery(User.class);
		Root<User>rootuser=criteriaquery.from(User.class);
		Predicate predicate = getPredicate(searchcriteria,rootuser);
		criteriaquery.where(predicate);
		setOrder(userpage,criteriaquery,rootuser);
		TypedQuery<User>typedquery=entitymanager.createQuery(criteriaquery);
		typedquery.setFirstResult(userpage.getPageno()*userpage.getPagesize());
		typedquery.setMaxResults(userpage.getPagesize());
		Pageable pageable=getPageable(userpage);
		long usercount=getUserCount(predicate);
		return new PageImpl<>(typedquery.getResultList(),pageable,usercount);
		
	}
	private long getUserCount(Predicate predicate) {
		// TODO Auto-generated method stub
		CriteriaQuery<Long> countquery=criteriabuilder.createQuery(Long.class);
		Root<User>countroot=countquery.from(User.class);
		countquery.select(criteriabuilder.count(countroot)).where(predicate);
		return entitymanager.createQuery(countquery).getSingleResult();
	}
	private Pageable getPageable(UserPage userpage) {
		Sort sort=Sort.by(userpage.getSortdirection(), userpage.getSortBy());
		return PageRequest.of(userpage.getPageno(), userpage.getPagesize(), sort);
	}
	private void setOrder(UserPage userpage, CriteriaQuery<User> criteriaquery, Root<User> rootuser) {
		// TODO Auto-generated method stub
		if(userpage.getSortdirection().equals(Sort.Direction.ASC)) {
			criteriaquery.orderBy(criteriabuilder.asc(rootuser.get(userpage.getSortBy())));
			
		}
		else {
			criteriaquery.orderBy(criteriabuilder.desc(rootuser.get(userpage.getSortBy())));
		}
		
	}
	private Predicate getPredicate(UserSearchCriteria searchcriteria, Root<User> rootuser) {
		// TODO Auto-generated method stub
		List<Predicate>predicates=new ArrayList<>();
		if(Objects.nonNull(searchcriteria.getPosh())) {
			String str=searchcriteria.getPosh();
					String a[]=str.split(",");
					String first=a[0];
					String second=a[1];
				
				
					predicates.add(criteriabuilder.like(rootuser.get("posh"), first));
					predicates.add(criteriabuilder.like(rootuser.get("posh"), second));
		}
		if(Objects.nonNull(searchcriteria.getXfc())) {
			predicates.add(criteriabuilder.like(rootuser.get("xfc"),searchcriteria.getXfc())
					);
		}
		if(Objects.nonNull(searchcriteria.getIsms())) {
			predicates.add(criteriabuilder.like(rootuser.get("isms"),searchcriteria.getIsms())
					);
		}
		if(Objects.nonNull(searchcriteria.getEmp())) {
			predicates.add(criteriabuilder.like(rootuser.get("emp"),searchcriteria.getEmp())
					);
		}
		return criteriabuilder.and(predicates.toArray(new Predicate[0]));
	}
	

}
