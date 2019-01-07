import java.util.Random;

public class Neuron
{
	protected float[] weights;
	protected float   learning_rate;
	
	public Neuron(int inputs, float lr)
	{
		weights        = new float[inputs];
		learning_rate  = lr;
		Random rnd     = new Random();
		
		for(int i = 0; i < inputs; i++)
			weights[i] = rnd.nextFloat();
	}
	
	public float think(float[] input)
	{
		float sum = 0.0f;
		for(int i = 0; i < weights.length; i++)
			sum += weights[i] * input[i];
		return 1.0f / ((float) Math.exp(-sum) + 1.0f);
	}
	
	public void train(float[] input, float expected_output)
	{
		float guess = think(input);
		float error = expected_output - guess;
		
		for(int i = 0; i < weights.length; i++)
			weights[i] += input[i] * error * learning_rate;
	}
	
}
