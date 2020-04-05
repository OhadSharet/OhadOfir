
import java.util.LinkedList;

public  class OhadInfra {

    static int test_counter = 0;
    static int pass_counter = 0;
    static int fail_counter = 0;
    static final boolean ENABLE_PRINT = true;
    static LinkedList<FailedTest> tests_failed = new LinkedList<FailedTest>();

    public static void report(String testcase, boolean res) {

        String msg;
        if (res)
            msg = "";
        else
            msg = "¯\\_(ツ)_/¯ (i don't know )" ;
        report(testcase,res,msg);
    }

    public static void report(String testcase, boolean res,String msg) {

        _update_test_counter(testcase,res,msg);
        if (ENABLE_PRINT) {
            System.out.println(testcase + ": " + (res ? "ok" : "WRONG !!!! :( "));
            System.out.println(msg);
            System.out.println();
        }
        else
        {
            if(test_counter%100==0)
                System.out.println("we have tested " + test_counter +" test cases so far and failed "+ fail_counter + " tests");
            if(!res)
            {
                System.out.println(testcase + ": " + (res ? "ok" : "WRONG !!!! :( "));
                System.out.println(msg);
            }
        }

    }

    public static void report(String testcase,String result,String excreted_result, boolean res)
    {
        report(testcase,result,excreted_result,res,"");
    }
    public static void report(String testcase,String result,String excreted_result, boolean res,String failure_msg) {
        String msg ;
        if (res)
            msg = "successfully got "+ result + " as expected ";
        else
            msg = "got " + result + " instead of " +excreted_result;

        if(failure_msg.length()!=0&& !res)
            msg += "\n"+failure_msg;

        report(testcase,res,msg);
    }



    public static void report(String testcase,String result,String excreted_result) {
        report(testcase,result,excreted_result,excreted_result.compareTo(result)==0);
    }
    public static void report(String testcase,int result,int excreted_result) {
        report(testcase,String.valueOf(result),String.valueOf(excreted_result),result== excreted_result);
    }

    private static void _update_test_counter(String testcase, boolean res,String msg)
    {
        test_counter++;
        if (res)
            pass_counter++;
        else
        {
            fail_counter++;
            tests_failed.addLast(new FailedTest(testcase,msg));
        }
    }


    public static void _print_arr(int[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
    }

    public  static void print_final_report()
    {
        System.out.println("####################### Summary ################################ ");
        System.out.println(test_counter + " tests have been executed");
        double final_grade = ((double)pass_counter/test_counter)*100;
        System.out.println("your final grade is " + final_grade);
        if(fail_counter!=0) {
            System.out.println("you have failed " + fail_counter + " tests");
            for(FailedTest ft: tests_failed)
                System.out.println(ft);
        }


    }



    private static void _give_verbal_grade(double final_grade)
    {
        if (final_grade == 100)
            System.out.println("perfect 100 , you have passed all of the tests");
        if(final_grade >70 && final_grade<100)
            System.out.println("you have failed a few tests");
        if(final_grade<50)
            System.out.println("you have failed most of the tests");
        else
            if(final_grade==0)
                System.out.println("you have failed every possible test.......... be ashamed ");
    }
}

class FailedTest {
    public String test_name;
    public String failure_reason;

    public FailedTest (String test_name,String failure_reason)
    {
        this.test_name= test_name;
        this.failure_reason = failure_reason;
    }
    public String toString()
    {
        return "test : \n" + test_name + "\n failed because : " + failure_reason;
    }
}

