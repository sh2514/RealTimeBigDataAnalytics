/* Shikuan Huang
 * Professor McIntosh
 * Realtime and Big Data Analytics
 * 19, February 2015
 * 
 * Assignment 3 - Page Rank Problem
 * Reducer
 */

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Assignment2Reducer
    extends Reducer<Text, Text, Text, Text> {
	
  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {
    
	float totalPR = 0;
	String targetPagesAndPR = "";
	for (Text value : values) {
	  try {
        totalPr += Float.parseFloat(value.toString());
	  }
	  catch(NumberFormatException e) {
	    targetPagesAndPR += value.toString();
	  }
	}
	
	targetPagesAndPR += String.valueOf(totalPR);
	
	context.write(key, new Text(targetPagesAndPR));
  }
}
