/* Shikuan Huang
 * Professor McIntosh
 * Realtime and Big Data Analytics
 * 19, February 2015
 * 
 * Assignment 3 - Page Rank Problem
 * Job Function
 */

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Assignment3Job {
  public static void main(String[] args) throws Exception {
	if (args.length < 2) {
	  System.err.println("Usage: Assignment3Job <input path> <output path>");
	  System.exit(-1);
	}
	
	Job job = new Job();
	job.setJarByClass(Assignment3Job.class);
	job.setJobName("Assignment 3");
	
	FileInputFormat.addInputPath(job, new Path(args[0]));
	FileOutputFormat.setOutputPath(job, new Path(args[1]));
	
	job.setMapperClass(Assignment3Mapper.class);
	job.setReducerClass(Assignment3Reducer.class);
	
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(Text.class);
	
	System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
