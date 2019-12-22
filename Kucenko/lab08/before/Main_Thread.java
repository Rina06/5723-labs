import java.io.IOException;

public class Main_Thread
{
	public static void main (String[] args) throws InterruptedException
	{
		int thread_count = 4;
		long time3;
		long time4;
		UsualMatrix m1 = new UsualMatrix (3, 3);
		UsualMatrix m2 = new UsualMatrix (3, 3);
		UsualMatrix res = new UsualMatrix (3, 3);
		UsualMatrix tmp = new UsualMatrix (3, 3);
		m1.setElement (0, 0, 1);
		m1.setElement (0, 1, 2);
		m1.setElement (0, 2, 3);
		m1.setElement (1, 0, 1);
		m1.setElement (1, 1, 2);
		m1.setElement (1, 2, 3);
		m1.setElement (2, 0, 1);
		m1.setElement (2, 1, 2);
		m1.setElement (2, 2, 3);
		m2.setElement (0, 0, 1);
		m2.setElement (0, 1, 2);
		m2.setElement (0, 2, 3);
		m2.setElement (1, 0, 1);
		m2.setElement (1, 1, 2);
		m2.setElement (1, 2, 3);
		m2.setElement (2, 0, 1);
		m2.setElement (2, 1, 2);
		m2.setElement (2, 2, 3);
		System.out.println(m1);
		System.out.println(m2);
		long time1 = System.currentTimeMillis ();
		m1.product (m2);
		long time2 = System.currentTimeMillis ();
		if (thread_count > m1.getRow ()) thread_count = m1.getRow ();
		int []mas = new int [thread_count];
		System.out.println ("Thread starts:");
		for (int i = 0; i < thread_count; i++)
		{
			mas = tmp.potok (i + 1, tmp.getColumn ());
			time3 = System.currentTimeMillis ();
			res = m1.productParallel (i + 1, m2, mas);
			time4 = System.currentTimeMillis ();
			System.out.print (res);
			System.out.print ( i + 1 + " thread: ");
			System.out.print (time4 - time3 + "ms");
			System.out.println ("\n");
		}
		long time5 = System.currentTimeMillis ();
		System.out.println ("One thread: " + (time2 - time1) + "ms");
		System.out.println ("all time threads: " + (time5 - time2) + "ms");
	}
}