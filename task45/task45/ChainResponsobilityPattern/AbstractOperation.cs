using System.Text.RegularExpressions;

namespace task45.ChainResponsobilityPattern;

public abstract class AbstractOperation : IOperation
{
    private IOperation _nextOperation;

    public IOperation SetNext(IOperation operation)
    {
        this._nextOperation = operation;

        return operation;
    }

    public virtual object Handle(object request)
    {
        return this._nextOperation != null ? this._nextOperation.Handle(request) : null;
    }

    protected bool TryParse(string input)
    {
        if (input is null) throw new ArgumentException("Given string is null!");
        var regexStr = new Regex(@"^-{0,1}[0-9]+ (\+|-|\*|\/) -{0,1}[0-9]+$");
        var copyStr = Regex.Replace(input, @"\s+", " ");
        return regexStr.IsMatch(copyStr);
    }

    protected DataStore Parse(string input)
    {
        var copyStr = Regex.Replace(input, @"\s+", " ");
        var splitted = copyStr.Split(" ");
        return new DataStore(long.Parse(splitted[0]), long.Parse(splitted[2]), splitted[1]);
    }
}