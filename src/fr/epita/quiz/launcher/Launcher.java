package fr.epita.quiz.launcher;

import java.util.Scanner;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Student;
import fr.epita.quiz.services.QuestionJDBCDAO;

public class Launcher {

	static Scanner scn = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Are you a student or Teacher? Press 1 for Student. Press 2 for Teacher.");
		
		int choice_Chosen = scn.nextInt();
		scn.nextLine();
		if(choice_Chosen==1)
		{
			studentoperations();
		}
		else
		{
			System.out.println("Please enter User Name:");
			String uname = scn.nextLine();
			System.out.println("Please enter your password:");
			String pwd = scn.nextLine();
			if(authenticateAdminmodule(uname,pwd))
			{
				System.out.println("Do you want to Add or update questions?");
				if("yes".equalsIgnoreCase(scn.nextLine()))
				{
					createOrUpdateQuestions();
				}
				else {
					System.out.println("Do you want to correct answers?");
					if("yes".equalsIgnoreCase(scn.nextLine()))
					{
						verifyAnswers();
					}
				}
			}
			else {
				System.out.println("Invalid User..");
			}
		}
		

	}

	private static void verifyAnswers() {
		// TODO Auto-generated method stub
		System.out.println("Please enter the student id to verify the answers:");
		int stdId = scn.nextInt();
		scn.nextLine();
		Student std = new Student();
		std.setStudId(stdId);
		QuestionJDBCDAO qnjdbc = new QuestionJDBCDAO();
		qnjdbc.verifyAnswers(std);
		System.out.println("Answers are checked..");
		
	}

	private static void createOrUpdateQuestions() {
		// TODO Auto-generated method stub
		System.out.println("DO you wish to add question?");
		if("yes".equalsIgnoreCase(scn.nextLine()))
		{
			System.out.println("Please enter the Question id?");
			int q_Id = scn.nextInt();
			scn.nextLine();
			System.out.println("Enter your question..");
			String question = scn.nextLine();
			System.out.println("Enter your answer");
			String ans = scn.nextLine();
			System.out.println("Option_A");
			String opt_A = scn.nextLine();
			System.out.println("Option_B");
			String opt_B = scn.nextLine();
			System.out.println("Option_C");
			String opt_C = scn.nextLine();
			System.out.println("Option_D");
			String opt_D = scn.nextLine();
			System.out.println("Enter the Topic..");
			String topic = scn.nextLine();
			System.out.println("Dificulty level..");
			int diff = scn.nextInt();
			scn.nextLine();
			Question ques = new Question();
			ques.setId(q_Id);
			ques.setQuestion(question);
			ques.setAnswer(ans);
			ques.setDifficulty(diff);
			ques.setTopics(topic);
			ques.setOption_A(opt_A);
			ques.setOption_B(opt_B);
			ques.setOption_C(opt_C);
			ques.setOption_D(opt_D);
			QuestionJDBCDAO conn = new QuestionJDBCDAO();
			conn.createQuestion(ques);
			System.out.println("Question added successfully..");
		}
		else {
			System.out.println("Do you wish to update question..!!");
			System.out.println("Please enter the Question id?");
			int qnId = scn.nextInt();
			scn.nextLine();
			System.out.println("Enter your question..");
			String question = scn.nextLine();
			System.out.println("Enter your answer");
			String answer = scn.nextLine();
			System.out.println("Option_A");
			String opt_A = scn.nextLine();
			System.out.println("Option_B");
			String opt_B = scn.nextLine();
			System.out.println("Option_C");
			String opt_C = scn.nextLine();
			System.out.println("Option_D");
			String opt_D = scn.nextLine();
			System.out.println("Enter the topic..");
			String topic = scn.nextLine();
			System.out.println("Difficulty level..");
			int diff = scn.nextInt();
			scn.nextLine();
			Question ques = new Question();
			ques.setId(qnId);
			ques.setQuestion(question);
			ques.setAnswer(answer);
			ques.setDifficulty(diff);
			ques.setTopics(topic);
			ques.setOption_A(opt_A);
			ques.setOption_B(opt_B);
			ques.setOption_C(opt_C);
			ques.setOption_D(opt_D);
			QuestionJDBCDAO conn = new QuestionJDBCDAO();
			conn.update(ques);		
			System.out.println("Question updated successfully..");
		}
	}

	private static boolean authenticateAdminmodule(String uname, String pwd) {
		// TODO Auto-generated method stub
		if("admin".equalsIgnoreCase(uname) && "hello".equalsIgnoreCase(pwd))
		{
			return true;
		}
		return false;
		
	}

	private static void studentoperations() {
		// TODO Auto-generated method stub
		System.out.println("Dear Student, Please enter the username");
		String username = scn.nextLine();
		System.out.println("Please enter your password..");
		String pwd = scn.nextLine();
		System.out.println("Id");
		int id = scn.nextInt();
		scn.nextLine();
		authenticateStdmodule(username, pwd , id);
		
		
	}


	private static void authenticateStdmodule(String username, String pwd, int id) {
		// TODO Auto-generated method stub
		Student std = new Student();
		std.setStudId(id);
		std.setPassword(pwd);
		std.setUsername(username);
		QuestionJDBCDAO db = new QuestionJDBCDAO();
		if(db.authenticateStudent(std))
		{
			System.out.println("Wanna take quiz..? Give it a try..");
			if("yes".equalsIgnoreCase(scn.nextLine())) {
				db.takequiz(scn, id);
			System.out.println("Quiz completed successfully..");}
		
			else
			{
				System.out.println("Wanna see your score..?");
				if("yes".equalsIgnoreCase(scn.nextLine()))
					db.checkScores(id);
				
			}
		}
		
		else
		{
			System.out.println("Invalid student ID");
		}
		
	}

}
