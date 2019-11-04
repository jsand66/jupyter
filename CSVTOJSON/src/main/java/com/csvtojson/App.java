package com.csvtojson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Please Provide first argument input filepath and second argument output file path");
		} else {
			File file = new File(args[0]);
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
				String st;
				st = br.readLine();
				if (st.contains("REAL TIME TRADE")) {
					OneSymbol sym = new OneSymbol();
					sym.symbolOne(args[0], args[1]);
				} else {
					MultipleSymbols ms = new MultipleSymbols();
					ms.symbolMutlple(args[0], args[1]);
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		OneSymbol ms = new OneSymbol();
//		//MultipleSymbols ms = new MultipleSymbols();
//		//ms.symbolMutlple("C:\\Users\\gambati\\Desktop\\multi", "C:\\Users\\gambati\\Desktop\\multi.json");
//		ms.symbolOne("C:\\Users\\gambati\\Desktop\\AAPL", "C:\\Users\\gambati\\Desktop\\AAPL.json");

	}

}