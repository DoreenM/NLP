import java.io.IOException;

/**
 * Created by Doreen on 2015-04-07.
 */
public class ReadTxt {

    public static void main(String[] arg)  throws IOException {
        String file_name = "C:/Users/Doreen/Desktop/RestaurantAnalysis/Restaurant-Evaluation-NLP/en_doccat.txt";

        try{
            ReadFile file = new ReadFile (file_name);
            String[] aryLines = file.OpenFile();
            int i;
            for(i=0; i<aryLines.length;i++){
                System.out.println(aryLines[i]);
            }


    }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }


}

