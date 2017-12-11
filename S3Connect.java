package s3connect;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class S3Connect {
	private static String bucketName = "YOUR_BUCKET";
	private static String key = "YOUR_FILE.csv";

	public static void main(String[] args) throws IOException, InterruptedException {
		
		AmazonS3 s3Client = new AmazonS3Client();
		try {
			System.out.println("Download started");
			S3Object s3object = s3Client.getObject(new GetObjectRequest(bucketName, key));

			InputStream in = s3object.getObjectContent();
			int count = 0;
			byte[] buf = new byte[1024];
			OutputStream out = new FileOutputStream(new File("C:YOUR_LOCAL_EXPORT_LOCATION\\S3.csv"));
			while ((count = in.read(buf)) != -1) {
				if (Thread.interrupted()) {
					throw new InterruptedException();
				}
				out.write(buf, 0, count);
			}
			out.close();
			in.close();
			System.out.println("Finished!");

		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which" + " means your request made it "
					+ "to Amazon S3, but was rejected with an error response" + " for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which means" + " the client encountered "
					+ "an internal error while trying to " + "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}
}