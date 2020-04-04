import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Student {
	   public String s;
	    int student_id;
	    String phone_no;  
	    String student_lastname;
	    String student_firstname,email,address,surname;    
	public Student(int studend_id,String email, String phone_no, String student_firstname) {    
	    this.student_id = studend_id;    
	    this.student_firstname = student_firstname;  
	    this.email = email;    
	    this.phone_no = phone_no; 
	    }
	public Student(int studend_id,String email, String phone_no, String student_firstname,String student_lastname) {    
	    this.student_id = studend_id;    
	    this.student_firstname = student_firstname;
	    this.student_lastname = student_lastname;
	    this.email = email;    
	    this.phone_no = phone_no; 
	    }
	public Student(int studend_id,String email,String phone_no, String student_firstname,String student_lastname,String address) {    
	    this.student_id = studend_id;    
	    this.student_firstname = student_firstname; 
	    this.student_lastname = student_lastname;
	    this.email = email;    
	    this.phone_no = phone_no; 
	    this.address=address;
	    }
	public static void getherInformation(String arr[],Map<Integer, Student> map){
		int rollno = Integer.parseInt(arr[0]);
		int len = arr.length;
		if (len==4){
			Student obj = new Student(rollno,arr[1],arr[2],arr[3]);
			map.put(rollno,obj);}
		else if(len==5){
			Student obj = new Student(rollno,arr[1],arr[2],arr[3],arr[4]);
			map.put(rollno,obj);
		}	
		else{
			Student obj = new Student(rollno,arr[1],arr[2],arr[3],arr[4],arr[5]);
			map.put(rollno,obj);
		}
	}
	public static void putData(Map map,List title){
		Iterator itr = title.iterator();
		while(itr.hasNext()){
	    	String data = (String)itr.next();
		    String subarray[] = data.split(" ");
		    getherInformation(subarray,map);   
	    }
	}
public String toString(){
	return ("Student Rollno :"+student_id+"\nStudent Email : "+email+"\nStudent Phone: "+phone_no+"\nStudent Name : "+student_firstname
			+"\nStudent LastName : "+student_lastname +"\nStudent City : "+address); 
}
public static void readingData(Map map) throws IOException{
	
	Path path = Paths.get("java1.txt").toAbsolutePath();
	List title = Files.lines(path).collect(Collectors.toList());
    
   	putData(map,title);
//   	Iterator itr = title.iterator();
//	while(itr.hasNext()){             for verify the data is store in file or not
   	
//    System.out.println(itr.next());
//    }
	
}

public static String putData(Map<Integer, Student> map){
    String info="";
    Scanner scan =new Scanner(System.in);
    System.out.println("Enter Student Information respectively");
    System.out.println("Enter Rollno : "); int rollno = scan.nextInt();
    System.out.println("Enter Email "); 
    scan.nextLine();
    String email= scan.nextLine();
    System.out.println("Enter Phone"); String phone_no = scan.nextLine();
    System.out.println("Enter First Name"); String firstname = scan.nextLine();
    System.out.println("Enter Last Name "); String lastname = scan.nextLine();
    System.out.println("Enter Only City/State"); String address = scan.nextLine();
    info = rollno+" "+email+" "+phone_no+" " + firstname+" "+address; 
    Student obj = new Student(rollno,email,phone_no,firstname,lastname,address);
    map.put(rollno,obj); 
   
   
    return info;
}

public static void searchData(int rollno,Map map){
	System.out.println(map.get(rollno));
	
}
static void insertStudent(Map map) throws IOException{
	    FileOutputStream fout=new FileOutputStream("java1.txt",true);
		String s = putData(map);
		byte[] b = s.getBytes(StandardCharsets.UTF_8);
		fout.write(b);
		String newLine = "\n";
		byte[] bb = newLine.getBytes(StandardCharsets.UTF_8);
		fout.write(bb);
		fout.close();
}


public static void delete(int rollno,Map map){
	map.remove(rollno);
	System.out.println("Successfully Done");
}

public static void query(Map map) throws IOException {
	boolean loop =true;
//	createTree();
	System.out.println("Perform Query ");
	Scanner sc =new Scanner(System.in);
	while(loop){
	System.out.println("\n\n Enter 1 : To input data\n Enter 2 : To display Data\n Enter 3 : For Delete Data \n Enter 4 : To exit \n ");
	
	int ch=sc.nextInt();
	if(ch==1){ insertStudent(map); }	 
	if(ch==2){ 
//		retriveData() for Entire File .
		System.out.println("Enter Student Rollno");
		int value = sc.nextInt();
		searchData(value,map);
		}
	if(ch==3){
		System.out.println("Enter Roll No");
		int key = sc.nextInt();
		delete(key,map);
	}
	
	if(ch==4){
		break;
	}
	}
    System.out.println("Thanks for Using our Automated Software");
    sc.close();
    }
public static void main(String args[]) throws IOException {
	  
	 Map<Integer,Student> map=new Hashtable<Integer,Student>();
	 readingData(map);
//	 query(map);
//	 
	 Console c=System.console(); 
	 char[] ch  =c.readPassword("Enter Password"); 
	 String pass=String.valueOf(ch);   
	 if(pass.equals("2409")){ query(map); }
	 else{ System.out.println("Invalid Password"); }		 
	 }

}
