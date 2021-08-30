package com.example.springbatchcsvtodatabase;

import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.slf4j.Logger;
public class UserItemProcessor implements ItemProcessor<User,User> {
    private static final Logger log= LoggerFactory.getLogger(UserItemProcessor.class);
    @Override
    public User process(User user) throws Exception {
        final int id= user.getId();
        final String name= user.getName();
        final User transuser=new User(id,name);
    }
}
