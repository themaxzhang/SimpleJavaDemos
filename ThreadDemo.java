/**
Simple example which demonstrates synchronisation on a shared resource between different threads.
Each spawned thread will increment the share resource without any duplicates of numbers
Removing the synchronized construct could result in incorrect incrementing

USAGE: java ThreadDemo [count]
*/
public class ThreadDemo implements Runnable
{
	public ThreadDemo(Resource r)
	{
		sharedResource = r;
	}

	Resource sharedResource;
	
	// This method will increment the share resource and print out the result as well as this thread.
	// moving the print statement outside the synchronized block will result into correct functionality but confusing print statements
	public void run()
	{
		synchronized (sharedResource)
		{
			sharedResource.increment();
			System.out.println("Thread Number:" + Thread.currentThread().getId() + " COUNT:" + sharedResource.getCount());
		}
	}

	public static void main(String args[])
	{
		int threadCount = Integer.parseInt(args[0]);
		// make sure we give everyone the same resource
		Resource r = new Resource(0);
		ThreadDemo h = new ThreadDemo(r);
		for (int i=1;i<=threadCount;++i)
		{
			new Thread(h).start();
		}
	}

	// class is only static for use in main method
	// note a static class in Java is different to one in C#
	private static class Resource
	{
		int count;
		public Resource(int count)
		{
			this.count = count;
		}

		public int getCount()
		{
			return this.count;
		}

		public void increment()
		{
			this.count++;
		}
	}
}
