
public class IO 
{
	public static void BubbleSort() {
		// TODO Auto-generated method stub
		boolean sorted = false;
		double[] data = new double[1000];
		for (int i = 0; i < 1000; i++){
			data[i] = Math.random();
		}
		//Perform bubble sort
		int n = data.length;
        double temp = 0.0;
        while (!sorted){
    	sorted = true;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (data[j-1] < data[j]) {
                	sorted = false;
                    temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                }
            }
        }
      }
	}
}

