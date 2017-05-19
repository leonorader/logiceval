package logiceval;

public class Main {

	public static void main(String[] args) {
		if(args.length >= 1) {
			new Console(args).parse();
		} else {
			System.out.println("vaaa - gui");
		}
	}

}
