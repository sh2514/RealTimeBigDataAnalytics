/* Shikuan Huang
 * Professor McIntosh
 * Realtime and Big Data Analytics
 * 19, February 2015
 * 
 * Assignment 3 - Page Rank Problem
 * Mapper
 */

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;

public class Assignment2Mapper
    extends Mapper<LongWritable, Text, Text, Text> {
  
  @Override
  public void map(LongWritable key, javax.xml.soap.Text value, Context context)
      throws IOException, InterruptedException {
	// Parse the value of each line
	String line = value.toString();
	StringTokenizer tokenize = new StringTokenizer(line);
	String sourcePage = tokenize.nextToken();
	float pageLinks = line.length() - 2;
	String[] targetPages = new String[pageLinks];
	for (int i = 0; i < pageLinks; i++) {
	  targetPages[i] = tokenize.nextToken();
	}
	// Emit each page's added PR value
	float currentPR = Float.parseFloat(tokenize.nextToken());
	String eachPagePR = String.valueOf(currentPR / pageLinks);
	for (int i = 0; i < pageLinks; i++) {
	  context.write(new Text(targetPages[i]), new Text(eachPagePR));
	}
	// Emit the source page's target pages 
	String listTargetPages = "";
	for (int i = 0; i < pageLinks; i++) {
	  listTargetPages += targetPages[i] + " ";
	}
	context.write(new Text(sourcePage), new Text(listTargePages));
	
	/*  
    String line = value.toString().toLowerCase();
    String confArg = "";
    boolean foundMatchForCurrentWord = false;
    Configuration conf = context.getConfiguration();
    
    int confArgCounter = 2;
    while ((confArg = conf.get("arg" + confArgCounter)) != null)
    {
      foundMatchForCurrentWord = false;
      
      Pattern seekWord = Pattern.compile(confArg.toLowerCase());
      Matcher matchLine = seekWord.matcher(line);
      while (matchLine.find())
      {
    	foundMatchForCurrentWord = true;
    	context.write(new Text(confArg), new Text("FOUND"));
      }
      if (!foundMatchForCurrentWord)
      {
    	context.write(new Text(confArg), new Text("NA"));
      }
      confArgCounter++;
    }
    */
  }
}
