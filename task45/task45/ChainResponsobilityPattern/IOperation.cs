namespace task45.ChainResponsobilityPattern;

public interface IOperation
{
    IOperation SetNext(IOperation operation);

    object Handle(object request);
}