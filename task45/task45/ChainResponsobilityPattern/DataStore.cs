namespace task45.ChainResponsobilityPattern;

public class DataStore
{
    public long FirstNumber { get; set; }
    
    public long SecondNumber { get; set; }
    
    public string Operation { get; set; }

    public DataStore(long firstNumber, long secondNumber, string operation)
    {
        FirstNumber = firstNumber;
        SecondNumber = secondNumber;
        Operation = operation;
    }
}