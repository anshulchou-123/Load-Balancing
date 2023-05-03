package com.loadbalance.major;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationArguments;

import org.springframework.context.event.EventListener;

import java.io.File;

@SpringBootApplication
public class MajorApplication{

    // @Override
    // public void run(ApplicationArguments args) throws Exception {
    //     // Code to be executed before the application starts listening
    //     System.out.println("Application started!");
	// 	try {
	// 		// create a process
	// 		ProcessBuilder pb = new ProcessBuilder("cmd");
	//         pb.directory(new File("C:\\Users\\hp\\Downloads\\major2222\\major\\src\\main\\kubernetes"));

	// 		// take all commands as input in a text file
	// 		File commands = new File("C:\\test\\commands.txt");

	// 		// File where error logs should be written
	// 		File error = new File("C:\\test\\error.txt");

	// 		// File where output should be written
	// 		File output = new File("C:\\test\\output.txt");

	// 		// redirect all the files
	// 		pb.redirectInput(commands);
	// 		pb.redirectOutput(output);
	// 		pb.redirectError(error);
			
	// 		// start the process
	// 		Process pro=pb.start();
	// 		pro.waitFor();
	// 		System.out.println("inside of the command code");
	// 	}
	// 	catch(Exception e)
	// 	{
	// 		e.printStackTrace();
	// 	}
    // }

	@EventListener(MajorApplication.class)
	public void doSomethingAfterStartup() {
		try {
			// create a process
			ProcessBuilder pb = new ProcessBuilder("cmd");
	        pb.directory(new File("C:\\Users\\hp\\Downloads\\major2222\\major\\src\\main\\kubernetes"));

			// take all commands as input in a text file
			File commands = new File("C:\\test\\commands.txt");

			// File where error logs should be written
			File error = new File("C:\\test\\error.txt");

			// File where output should be written
			File output = new File("C:\\test\\output.txt");

			// redirect all the files
			pb.redirectInput(commands);
			pb.redirectOutput(output);
			pb.redirectError(error);
			System.out.println("inside of the command code");

			// start the process
			pb.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(MajorApplication.class, args);
	}


}
