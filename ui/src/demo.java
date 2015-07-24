abstract class Example1{
   private int numOne=10;
   protected final int numTwo=20;
   public static final int numThree=500;
   public static String test; 
   public void display1(String val){
      System.out.println("Num1="+numOne);
      test = val;
      
   }
}
class Example2 extends Example1{
   public void display2(){
      System.out.println("Num2="+numTwo);
      System.out.println("Num3="+numThree);
      System.out.println("argment="+numTwo);
   }
}
class Demo{
   public static void main(String args[]){
      Example2 obj=new Example2(); 
      obj.display1("smila");
      obj.display2();
   }
}