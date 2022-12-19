namespace task45.ChainResponsobilityPattern;

public class MultiplyOperation : AbstractOperation
{
    public override object Handle(object request)
    {
        string copyRequest = request as string;
        if (TryParse(copyRequest))
        {
            DataStore store = Parse(copyRequest);
            if (store.Operation.Equals("*"))
            {
                var result = store.FirstNumber * store.SecondNumber;
                return $"{store.FirstNumber} * {store.SecondNumber} = {result}";
            }
            else
            {
                return base.Handle(request);
            }
        }
        else
        {
            throw new ArgumentException("Invalid string was given!");
        }
    }
}