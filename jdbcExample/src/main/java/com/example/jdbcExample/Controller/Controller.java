package com.example.jdbcExample.Controller;

import com.example.jdbcExample.Entity.Employee;
import com.example.jdbcExample.Repository.JdbcEmployeeRepository;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import de.micromata.opengis.kml.v_2_2_0.*;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.jsoup.nodes.Element;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.*;


@RestController
@RequestMapping("Employee")
public class Controller {

    @Autowired
    private JdbcEmployeeRepository repository;

    @Value("classpath:data/sample.json")
    Resource appointmentsResource;

    @Autowired
    private ObjectMapper objectMapper;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().findAndRegisterModules()
            .registerModule(new SimpleModule());

    @PostMapping("api/save")
    public Employee saveEmployee(@Valid @RequestBody Employee employee){
          return repository.save(employee);
    }

    @GetMapping("/api/getAll")
    public List<Employee> getEmployees(){
        return repository.findAll();
    }
    @GetMapping("/api/getById/{id}")
    public Employee getEmpById(@PathVariable long id){
        return repository.findById(id);
    }
    @GetMapping("/api/getTwo/{department}/{address}")
    public List<Employee> found(@PathVariable String department,@PathVariable String address){
        return repository.findByDepartmentOrAddress(department,address);
    }
    @GetMapping("/api/getByPage/{pageNo}/{pageSize}")
    public List<Employee>getPage(@PathVariable int pageNo,@PathVariable int pageSize){
        List<Employee> emp = repository.getPage(pageNo,pageSize);
        return  emp;
    }

 /*   @GetMapping("/api/get1")
    public void test() throws IOException, JAXBException, ParserConfigurationException, SAXException, XPathExpressionException {
       String html ="<html>" +
               "<table style=\"font-family:Arial,Verdana,Times;font-size:12px;text-align:left;width:100%;border-collapse:collapse;padding:3px 3px 3px 3px\">\n" +
               "\n" +
               "<tr style=\"text-align:center;font-weight:bold;background:#9CBCE2\">\n" +
               "\n" +
               "<td>Mobily</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr>\n" +
               "\n" +
               "<td>\n" +
               "\n" +
               "<table style=\"font-family:Arial,Verdana,Times;font-size:12px;text-align:left;width:100%;border-spacing:0px; padding:3px 3px 3px 3px\">\n" +
               "\n" +
               "<tr>\n" +
               "\n" +
               "<td>OPERATOR</td>\n" +
               "\n" +
               "<td>Mobily</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr bgcolor=\"#D4E4F3\">\n" +
               "\n" +
               "<td>ODB_TB</td>\n" +
               "\n" +
               "<td>KHB-NKHB-RAKA-03-8209-A</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr>\n" +
               "\n" +
               "<td>CITY</td>\n" +
               "\n" +
               "<td>Khobar</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr bgcolor=\"#D4E4F3\">\n" +
               "\n" +
               "<td>DISTRICT</td>\n" +
               "\n" +
               "<td>Al Khobar Shamaliyyah</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr>\n" +
               "\n" +
               "<td>PROVINCE</td>\n" +
               "\n" +
               "<td>Eastern</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr bgcolor=\"#D4E4F3\">\n" +
               "\n" +
               "<td>REGION</td>\n" +
               "\n" +
               "<td>Eastern</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr>\n" +
               "\n" +
               "<td>Saleable</td>\n" +
               "\n" +
               "<td>YES</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr bgcolor=\"#D4E4F3\">\n" +
               "\n" +
               "<td>LAT_Y</td>\n" +
               "\n" +
               "<td>26.299201</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr>\n" +
               "\n" +
               "<td>LONG_X</td>\n" +
               "\n" +
               "<td>50.220595</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr bgcolor=\"#D4E4F3\">\n" +
               "\n" +
               "<td>HH_TOTAL</td>\n" +
               "\n" +
               "<td>&lt;Null&gt;</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr>\n" +
               "\n" +
               "<td>Ports_Total</td>\n" +
               "\n" +
               "<td>2</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr bgcolor=\"#D4E4F3\">\n" +
               "\n" +
               "<td>Ports_Used</td>\n" +
               "\n" +
               "<td>0</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr>\n" +
               "\n" +
               "<td>Ports_Avail</td>\n" +
               "\n" +
               "<td>2</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr bgcolor=\"#D4E4F3\">\n" +
               "\n" +
               "<td>TYPE</td>\n" +
               "\n" +
               "<td>FTTH</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr>\n" +
               "\n" +
               "<td>Date_Received</td>\n" +
               "\n" +
               "<td>&lt;Null&gt;</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "<tr bgcolor=\"#D4E4F3\">\n" +
               "\n" +
               "<td>UAN_Dawiyat</td>\n" +
               "\n" +
               "<td>&lt;Null&gt;</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "</table>\n" +
               "\n" +
               "</td>\n" +
               "\n" +
               "</tr>\n" +
               "\n" +
               "</table>"+
               "</html>";
        Document document = Jsoup.parse(html);
        Elements elements = document.select("table > tbody > tr");
        for (Element element : elements) {
            Elements titleLists = element.select("table > tbody > tr");
            for (Element tr : titleLists) {
                String data = tr.select("td").text();
                System.out.println(data);

            }
        }*/
//  kml file parsing using Document and placemark
    @GetMapping("/api/odb")
    public void dummy() throws IOException, JAXBException {
       /* JAXBContext jc = JAXBContext.newInstance(Kml.class);
        Unmarshaller u = jc.createUnmarshaller();
        Kml kml = (Kml) u.unmarshal(new File("src/main/resources/doc.kml"));
        Document document = (Document) kml.getFeature();
        Folder folder = (Folder) document.getFeature().get(0);
        for (int i = 0; i < 1; i++) {
            Placemark placemaek2D = (Placemark) folder.getFeature().get(0);
            String description = placemaek2D.getDescription();
            org.jsoup.nodes.Document jsoupDocument = Jsoup.parse(description);
            Elements elements = jsoupDocument.select("table > tbody > tr");
            Odb odb = new Odb();
            odb.setId(UUID.randomUUID().toString());
            Points points = new Points();
            for (Element element : elements) {
                Elements titleLists = element.select("table > tbody > tr");
                int count = 0;
                HashMap<String,String> hmap = new HashMap<>();
                for (Element tr : titleLists) {
                    String  data = tr.select("td").get(0).text();
                    String data2 = tr.select("td").get(1).text();
                    hmap.put(data,data2);
                       if (count == 0) {
                            odb.setOperator(data);
                        }
                        if (count == 1) {
                            odb.setName(data);
                        }
                        if (count == 7) {
                            points.setLon(data);
                        }
                        if (count == 8) {
                            points.setLat(data);
                        }
                        if (count == 10) {
                            if(data == "" || null == data)
                                odb.setPortsUsed(0L);
                            else
                            odb.setPortsUsed(Long.parseLong(data));
                        }
                        if (count == 11) {
                            if(data == "" || null == data)
                                odb.setPortsUsed(0L);
                            else
                            odb.setPortsTotal(Long.parseLong(data));
                        }

                   count++;

                }
                for(Map.Entry<String,String> entry:hmap.entrySet() ){
                    System.out.print(entry.getKey() +" "+entry.getValue());
                    System.out.println();
                }

            }
            odb.setLocation(points.toString());
        }*/

    }


   /*  @PostMapping("/api/upload")
     public  void upload(@ModelAttribute MultipartFile file) throws IOException {
         File zip = File.createTempFile(UUID.randomUUID().toString(), "temp");
         FileOutputStream o = new FileOutputStream(zip);
         IOUtils.copy(file.getInputStream(), o);
         o.close();
         String destination = "src/main/resources/gis";
         try {
             ZipFile zipFile = new ZipFile(zip);
             zipFile.extractAll(destination);
             JAXBContext jc = JAXBContext.newInstance(Kml.class);
             Unmarshaller u = jc.createUnmarshaller();
             Kml kml = (Kml) u.unmarshal(new File("src/main/resources/gis/doc.kml"));
             Document document = (Document) kml.getFeature();
             Folder folder = (Folder) document.getFeature().get(0);
             for (int i = 0; i < folder.getFeature().size(); i++) {
                 Placemark placemaek2D = (Placemark) folder.getFeature().get(0);
                 String description = placemaek2D.getDescription();
                 org.jsoup.nodes.Document jsoupDocument = Jsoup.parse(description);
                 Elements elements = jsoupDocument.select("table > tbody > tr");
                 Odb odb = new Odb();
                 odb.setId(UUID.randomUUID().toString());
                 Points points = new Points();
                 for (Element element : elements) {
                     Elements titleLists = element.select("table > tbody > tr");
                     int count = 0;
                     for (Element tr : titleLists) {
                         String  data = tr.select("td").get(1).text();
                         if (count == 0) {
                             odb.setOperator(data);
                         }
                         if (count == 1) {
                             odb.setName(data);
                         }
                         if (count == 7) {
                             points.setLon(data);
                         }
                         if (count == 8) {
                             points.setLat(data);
                         }
                         if (count == 10) {
                             if(data == "" || null == data)
                                 odb.setPortsUsed(0L);
                             else
                                 odb.setPortsUsed(Long.parseLong(data));
                         }
                         if (count == 11) {
                             if(data == "" || null == data)
                                 odb.setPortsUsed(0L);
                             else
                                 odb.setPortsTotal(Long.parseLong(data));
                         }

                         count++;
                     }

                 }
                 odb.setLocation(points.toString());
                 System.out.println(odb);
                 odbRespository.save(odb);
             }
         } catch (ZipException | JAXBException e) {
             e.printStackTrace();
         } finally {
             zip.delete();
          }
        }
    @GetMapping("/api/5g")
    public void Data() throws IOException, JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Kml.class);
        Unmarshaller u = jc.createUnmarshaller();
        Kml kml = (Kml) u.unmarshal(new File("src/main/resources/5gdoc.kml"));
        Document document = (Document) kml.getFeature();
        Folder folder = (Folder) document.getFeature().get(0);
        int count =0;
        for (int i = 0; i < folder.getFeature().size(); i++) {
            Placemark placemaek2D = (Placemark) folder.getFeature().get(0);
            String description = placemaek2D.getDescription();
            org.jsoup.nodes.Document jsoupDocument = Jsoup.parse(description);
            FiveG fiveG = new FiveG();
            fiveG.setId(UUID.randomUUID().toString());
            Elements elements = jsoupDocument.select("table > tbody > tr");
            for (Element element : elements) {
                Elements titleLists = element.select("table > tbody > tr");
                for (Element tr : titleLists) {
                    String data = tr.select("td").get(1).text();
                    fiveG.setSignalStrength(data);

                }
            }
            count++;
            MultiGeometry multiGeometry = (MultiGeometry) placemaek2D.getGeometry();
            List<Geometry> geometryList = multiGeometry.getGeometry();
            Polygon Poly = (Polygon) geometryList.get(0);
            List<Coordinate> coordList = Poly.getOuterBoundaryIs().getLinearRing().getCoordinates();
            List<String> cords = new ArrayList<>();
            for (Coordinate point : coordList) {
               cords.add(Double.toString(point.getLatitude()));
               cords.add(Double.toString(point.getLongitude()));
            }
            String value = cords.toString();
            fiveG.setPolygon(value);
            System.out.println(fiveG);

          /*  List<Boundary> boundaries  = Poly.getInnerBoundaryIs();
            System.out.print("-------------------InnerBoundary ---------------------------");
            for(Boundary boundary : boundaries) {
                List<Coordinate> coordinates = boundary.getLinearRing().getCoordinates();
                System.out.print("---------------mini inner boundary-----------------------------");
                for (Coordinate point : coordinates) {
                    System.out.println(point.getLatitude() + " " + point.getLongitude() + " " + point.getAltitude());
                }

            }

        }*/
       // }
      //  System.out.println(count);
    //}
     /*   public Odb getAll(Elements titleLists,int count,Points points,Odb odb) {
       count = 0;
       for (Element tr : titleLists) {
           String data = tr.select("td").get(1).text();
           if (count == 0) {
               odb.setOperator(data);
           }
           if (count == 1) {
               odb.setName(data);
           }
           if (count == 7) {
               points.setLon(data);
           }
           if (count == 8) {
               points.setLat(data);
           }
           if (count == 10) {
               if (data == "" || null == data)
                   odb.setPortsUsed(0L);
               else
                   odb.setPortsUsed(Long.parseLong(data));
           }
           if (count == 11) {
               if (data == "" || null == data)
                   odb.setPortsUsed(0L);
               else
                   odb.setPortsTotal(Long.parseLong(data));
           }
           count++;
       }
       odb.setLocation(points.toString());
       return odb;
   }

    @GetMapping("/get")
    public void getFile(@RequestPart MultipartFile file,String type,MultipartFile file2) throws IOException, ParseException {
        if (type.equals("JSON")) {
            JSONParser parser = new JSONParser();
            // JSONArray jsonArray =new ObjectMapper().readValue(file.getBytes(), JSONArray.class);
            JSONArray jsonArray = (JSONArray) parser.parse(new InputStreamReader(file.getInputStream(), "UTF-8"));

            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                String name = (String) jsonObject.get("name");
                System.out.println("Name : " + name);
                String operator = (String) jsonObject.get("operator");
                System.out.println("Operator : " + operator);
                Long port_total = (Long) jsonObject.get("ports_total");
                System.out.println("PortTotal : " + port_total);
                Long port_used = (Long) jsonObject.get("ports_used");
                System.out.println("Employee Number : " + port_used);
                JSONArray location = (JSONArray) jsonObject.get("location");
                for (int i = 0; i < location.size(); i++) {
                    String st = location.get(i).toString();
                    st = st.substring(1, st.length() - 1);
                    String arr[] = st.split(",");
                    System.out.println(arr[0]);
                    System.out.println(arr[1]);
                    System.out.println(arr[2]);
                }


            }

        }
    }
/// json schema validation and parsing using jsonfactory
    @GetMapping("/getAll")
    public void getJson() throws IOException {
      /*  ObjectMapper objectMapper = new ObjectMapper();

        // create an instance of the JsonSchemaFactory using version flag
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance( SpecVersion.VersionFlag.V201909);

        // store the JSON data in InputStream
        try(

                InputStream jsonStream = file.getInputStream();
                InputStream schemaStream = file2.getInputStream();
        ){

            // read data from the stream and store it into JsonNode
            JsonNode json = objectMapper.readTree(jsonStream);

            // get schema from the schemaStream and store it into JsonSchema
            JsonSchema schema = schemaFactory.getSchema(schemaStream);

            // create set of validation message and store result in it
            Set<ValidationMessage> validationResult = schema.validate( json );

            // show the validation errors
            if (validationResult.isEmpty()) {

                // show custom message if there is no validation error
                System.out.println( "There is no validation errors" );

            } else {

                // show all the validation error
                validationResult.forEach(vm -> System.out.println(vm.getMessage()));
            }*/
    // json schema validation part
     /*   InputStream schemaAsStream = Controller.class.getClassLoader().getResourceAsStream("model/OdbJson.schema.json");
        JsonSchema schema = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V6).getSchema(schemaAsStream);
        InputStream InputStream = Controller.class.getClassLoader().getResourceAsStream("model/sample.json");
        ObjectMapper om = new ObjectMapper();
        om.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        JsonNode jsonNode = om.readTree(InputStream);

        Set<ValidationMessage> errors = schema.validate(jsonNode);
        String errorsCombined = "";
        if(errors.size() == 0){
            System.out.println("no error");
        }
        for (ValidationMessage error : errors) {
            System.out.println( error);
            errorsCombined += error.toString() + "\n";
        }

        if (errors.size() > 0)
            throw new RuntimeException("Please fix your json! " + errorsCombined);
        }*/
    }





