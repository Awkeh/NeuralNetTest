
public abstract class NeuralNet
{
	static Neuron n;
	
	static float[][] training_in =
	{
	    { 255.0f,   0.0f,   0.0f },
	    {   0.0f, 255.0f,   0.0f },
	    {   0.0f,   0.0f, 255.0f },
	    { 255.0f,   0.0f, 255.0f },
	    { 255.0f, 255.0f, 255.0f },
	    {   0.0f,   0.0f,   0.0f },
	    { 170.0f, 255.0f, 128.0f },
	    { 255.0f, 102.0f, 204.0f },
	    { 128.0f, 255.0f, 234.0f },
	    { 153.0f, 255.0f, 153.0f },
	    {  85.0f, 102.0f,   0.0f },
	    {   0.0f, 102.0f, 102.0f },
	    {  38.0f,   0.0f,  77.0f },
	    { 179.0f, 119.0f,   0.0f },
	    { 136.0f , 77.0f, 255.0f },
		{ 204.0f, 204.0f, 204.0f },
		{ 153.0f, 153.0f, 153.0f },
		{ 102.0f, 102.0f, 102.0f },
		{  51.0f,  51.0f,  51.0f },
		{ 153.0f, 204.0f, 255.0f },
		{  51.0f, 204.0f, 255.0f },
		{   0.0f, 204.0f, 204.0f },
		{   0.0f, 102.0f, 102.0f },
		{ 153.0f, 153.0f, 255.0f },
		{  51.0f, 102.0f, 255.0f },
		{   0.0f, 102.0f, 204.0f },
		{   0.0f, 102.0f, 102.0f },
		{ 153.0f, 153.0f, 255.0f },
		{  51.0f,  51.0f, 255.0f },
		{   0.0f,   0.0f, 204.0f },
		{   0.0f,   0.0f, 102.0f },
		{ 153.0f, 153.0f, 255.0f },
		{ 102.0f,  51.0f, 255.0f },
		{ 102.0f,   0.0f, 255.0f },
		{ 102.0f,   0.0f, 204.0f },
		{ 102.0f,   0.0f, 102.0f },
		{ 255.0f, 153.0f, 204.0f },
		{ 255.0f,  51.0f, 204.0f },
		{ 204.0f,   0.0f, 204.0f },
		{ 102.0f,   0.0f, 102.0f },
		{ 255.0f, 153.0f, 153.0f },
		{ 255.0f,  51.0f, 102.0f },
		{ 204.0f,   0.0f, 102.0f },
		{ 102.0f,   0.0f, 102.0f },
		{ 255.0f, 153.0f, 153.0f },
		{ 255.0f,  51.0f,  51.0f },
		{ 204.0f,  51.0f,   0.0f },
		{ 102.0f,  51.0f,   0.0f },
		{ 255.0f, 153.0f, 153.0f },
		{ 255.0f, 153.0f,  51.0f },
		{ 204.0f, 153.0f,   0.0f },
		{ 102.0f, 102.0f,   0.0f },
		{ 204.0f, 255.0f, 153.0f },
		{ 204.0f, 255.0f,  51.0f },
		{ 204.0f, 204.0f,   0.0f },
		{ 102.0f, 102.0f,   0.0f },
		{ 153.0f, 255.0f, 153.0f },
		{ 153.0f, 255.0f,  51.0f },
		{ 153.0f, 204.0f,   0.0f },
		{ 102.0f, 102.0f,   0.0f },
		{ 153.0f, 255.0f, 153.0f },
		{  51.0f, 255.0f,  51.0f },
		{   0.0f, 153.0f,   0.0f },
		{   0.0f, 102.0f,   0.0f },
		{ 153.0f, 255.0f, 153.0f },
		{   0.0f, 255.0f, 153.0f },
		{   0.0f, 102.0f, 102.0f }
	};
	static float[] training_out = new float[training_in.length];
	
	public static void train(int training)
	{
		NeuralNet.n = new Neuron(3, 0.1f);
		
		for(int i = 0; i < NeuralNet.training_out.length; i++)
		{
		    // Calculating expected output
			float r = NeuralNet.training_in[i][0];
			float g = NeuralNet.training_in[i][1];
			float b = NeuralNet.training_in[i][2];
			
			float M = Math.max(Math.max(r, g), b);
			float m = Math.min(Math.min(r, g), b);
			
			NeuralNet.training_out[i] = 0.5f * (M-m) >= 0.8f ? 1.0f : 0.0f;
		        
		    // Normalizing input values
		    NeuralNet.training_in[i][0] /= 255.0f;
		    NeuralNet.training_in[i][1] /= 255.0f;
		    NeuralNet.training_in[i][2] /= 255.0f;
		}
		
		// Training the neuron
		for(int d = 0; d < training; d++)
		{
		    for(int i = 0; i < NeuralNet.training_in.length; i++)
		    	NeuralNet.n.train(NeuralNet.training_in[i], NeuralNet.training_out[i]);
		}
	}

	public static boolean isLight(float r, float g, float b)
	{
		float[] input = { r, g, b };
		float guess = NeuralNet.n.think(input);
		
		if(guess >= 0.85f)
			return true;
		else
			return false;
	}
	
}
