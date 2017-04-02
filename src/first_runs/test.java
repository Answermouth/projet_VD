package first_runs;

import java.io.FileInputStream;

import org.apache.james.mime4j.stream.EntityState;
import org.apache.james.mime4j.stream.MimeTokenStream;

public class test {

    static public void main(String[] args) {
        try {
            MimeTokenStream stream = new MimeTokenStream();
            stream.parse(new FileInputStream("src/res/messages.msg"));
            for (EntityState state = stream.getState();
                 state != EntityState.T_END_OF_STREAM;
                 state = stream.next()) {
                
              switch (state) {
                case T_BODY:
                  System.out.println("Body detected, contents = "
                    + stream.getInputStream() + ", header data = "
                    + stream.getBodyDescriptor());
                  break;
                case T_FIELD:
                  System.out.println("Header field detected: "
                    + stream.getField());
                  break;
                case T_START_MULTIPART:
                  System.out.println("Multipart message detexted,"
                    + " header data = "
                    + stream.getBodyDescriptor());
                default:
                    break;            
              }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
