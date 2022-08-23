import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*

operations:
blue = minus 1
red = plus 1

----------------

3 1 3 1 3
R B R R B

1 1 3 3 3
B R R R B

1 2 3 4 5 (answer)
B R B R R

----------------

1 2 3 4 5
B R B R R

5 1 5 1 5
R B R R B

1 1 5 5 5
B R R R B

impossible (answer)

----------------

-2 -1 4 0
 R  R R R
 
-2 -1 0 4
 R  R R R
 
1 2 3 4 (answer)
R R R R

----------------

1 2 5 2
B R B R

1 2 2 5
B R R B

1 2 3 4 (answer)
B R R B

----------------

n = 10
3 4 4 5 5 6 6 7 8 8 
B R R B R R B B R B

3 5 6 7 8	4 4 5 6 8 
B B B B B	R R R R R

1 2 3 4 5	6 7 8 9 10
B B B B B	R R R R R

1 2 3 4 5 6 7 8 9 10
B B B R R B B R R R

----------------

n = 10
1 1 2 2 2 5 7 7 8 10 
B R B R R R R R R B 

distance of numbers from 1 and n

1 2 3 4 5 6 7 8 9 10
B B R R R R R R R B

----------------

n = 4
..., -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, ...

assumptions:
if its color is blue, we shouldn't decrease if it's 1
if its color is red, we shouldn't increase if it's n

there can only be one element which is 1 and blue
there can only be one element which is n and red

there should be no negative or zero with color blue (all x <= 0 should be R)
there should be no x > n with color red (all x > n should be B)

1
9
2 8 2 4 20 8 14 14 20 
BBRRBRRRR

 */

public class Main {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt();
			int[] a = fs.readArray(n);
			char[] colors = fs.next().toCharArray();
			ArrayList<Element> arr = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				arr.add(new Element(a[i], colors[i]));
			}
			Collections.sort(arr, new Comparator<Element>() {
				@Override
				public int compare(Element o1, Element o2) {
					if (o1.color == o2.color) {
						if (o1.num < o2.num) {
							return -1;
						}
						if (o1.num == o2.num) {
							return 0;
						}
						return 1;
					}
					if (o1.color == 'B') {
						return -1;
					}
					return 1;
				}
			});
//			for (Element el : arr) {
//				System.out.print(el.num + " ");
//			}
//			System.out.println();
//			for (Element el : arr) {
//				System.out.print(el.color + " ");
//			}
//			System.out.println();
			boolean valid = true;
			for (int i = 0; i < arr.size(); i++) {
				int currentNum = i + 1;
				boolean checker = false;
				if (arr.get(i).color == 'B') {
					if (1 <= currentNum && currentNum <= arr.get(i).num) {
						checker = true;
					}
				} else {
					if (currentNum >= arr.get(i).num) {
						checker = true;
					}
				}
				if (!checker) {
					valid = false;
					break;
				}
			}
			out.println(valid ? "YES" : "NO");
		}
		out.close();
	}
	
	static class Element {
		int num;
		char color;
		
		Element(int num, char color) {
			this.num = num;
			this.color = color;
		}
	}
	
	static void sort(int[] a) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int x : a) {
			arr.add(x);
		}
		Collections.sort(arr);
		for (int i = 0; i < a.length; i++) {
			a[i] = arr.get(i);
		}
	}
	
	static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
