import java.util.*;

public class ImprovedKBC {

	private static Scanner sc;

	public static class Questions {
		String question = "hel";
		String correctOption;
		int correctNum;
		String options[] = new String[4];

		Questions(String q, String c, String arr[], int num) {
			question = q;
			correctOption = c;
			options = arr;
			correctNum = num;
		}
	}

	static Questions ques[] = new Questions[10];

	public static void feedQues() {
		// ques1
		String options1[] = { "Tea", "Fruit Juice", "Coffee", "Lassi" };
		ques[0] = new Questions("Cold brew, Latte, Espresso are all example of?", "Coffee", options1, 2);
		// ques2
		String options2[] = { "Hate", "Love", "Down", "Up" };
		ques[1] = new Questions("Which of these is a term used for score in racquet sports", "Love", options2, 1);
		// ques3
		String options3[] = { "Astrology", "Journalism", "Medicine", "Law" };
		ques[2] = new Questions("Rajat Sharma, Sonia Singh and Rahul Kanwal are associated by which profession",
				"Journalism", options3, 1);
		// ques4
		String options4[] = { "Salat", "Zakat", "Hadith", "Hajj" };
		ques[3] = new Questions("Which of these does not feature in five pillar of Islam", "Hadith", options4, 2);
		// ques5
		String options5[] = { "Jharkhand", "Odisha", "Bihar", "West Bengal" };
		ques[4] = new Questions("In which state of India is the town Jamtara located?", "Jharkhand", options5, 0);
	}

	public static int lifeLine(int qn) {
		sc = new Scanner(System.in);
		System.out.println("Which lifeLine do you want:-");
		System.out.println("1--> 50-50");
		System.out.println("2 --> double dip");
		int ll = sc.nextInt();
		switch (ll) {
		case 1: // 50-50
			List<Integer> ra = new ArrayList<>();
			int p = 0;
			while (p < 4) {
				if (ques[qn].correctOption != ques[qn].options[p]) {
					ra.add(p);
				}
				p++;
			}
			int idx = new Random().nextInt(ra.size());
			if (ra.get(idx) < ques[qn].correctNum) {
				System.out.println(ra.get(idx) + 1 + " " + ques[qn].options[ra.get(idx)]);
				System.out.println(ques[qn].correctNum + 1 + " " + ques[qn].correctOption);
			} else {
				System.out.println(ques[qn].correctNum + 1 + " " + ques[qn].correctOption);
				System.out.println(ra.get(idx) + 1 + " " + ques[qn].options[ra.get(idx)]);
			}
			int ansf = sc.nextInt();
			return ansf;
		case 2: // double dip
			for (int i = 0; i < 2; i++) {
				System.out.println("double dip is active take try");
				int ans = sc.nextInt();
				if (ans < 0 && ans > 4) {
					return -1;
				}
				if (ques[qn].correctOption == ques[qn].options[ans - 1]) {
					return ans;
				}
			}
			break;
		default:
			System.out.println("Violation of lifeline");
			return -1;

		}

		return -1;
	}

	public static void rules() {
		System.out.println("-----------------------------------------------------------------");
		System.out.println("... You have two Lifelines in this whole game.");
		System.out.println("--50-50 --> Erase half wrong options");
		System.out.println("--Double dip --> Give a extra chance to answer if you get wrong");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("... For every right answer you will be awarded 10000 points");
		System.out.println("... You can quit anytime in the game by clicking q over promting to quit");
		System.out.println(
				"***But you will be awarded 0 opints as prize if you want to quit before answering two questions***");
		System.out.println(
				"... Over violating any of the rules like trying to feed option which is not available you will be out of game");
		System.out.println("Read all rules and to start the game press any key ...... ");
		System.out.println(sc.next().charAt(0) + "....Lets start the game....");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sc = new Scanner(System.in);
		System.out.println("Welcome to Kon Banega Crorepati");
		rules();
		feedQues();

		char c;
		int q = 0;
		int tll = 0;
		int ans = -1;
		int prize = 0;
		do {
			System.out.println("Your question is over the screen:-");
			System.out.println("Ques" + (q + 1) + ". " + ques[q].question);
			System.out.println("Options:-");
			for (int i = 0; i < 4; i++) {
				System.out.println(i + 1 + ". " + ques[q].options[i]);
			}
			System.out.print("Do you want to quit the game then press q else any other key ");
			c = sc.next().charAt(0);
			if (c == 'q' || c == 'Q') {
				break;
			}
			if (tll < 2) {
				System.out.println("if you want to take life line please click 'y' or anyother key to give ans ");
				char l = sc.next().charAt(0);
				if ((l == 'y' || l == 'Y')) {
					tll++;
					ans = lifeLine(q);
				} else {
					System.out.print("Give answer: ");
					ans = sc.nextInt();
				}
			} else {
				System.out.print("Give answer: ");
				ans = sc.nextInt();
			}
			if (ans > 0 && ans < 5 && ques[q].correctOption == ques[q].options[ans - 1]) {
				prize = prize + 10000;
			} else {
				break;
			}
			System.out.println("Now you have " + prize + " points.");
			q++;
		} while (q < 5);

		if (q == 5) {
			System.out.println("You won");
			System.out.println("You have " + prize + " points");
		} else if (q < 2) {
			System.out.println("Thanks for playing");
		} else {
			System.out.println("Thanks for playing you have" + prize + " points");
		}
	}

}
