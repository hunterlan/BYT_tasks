using MediatrPattern;
using ObjectPoolPattern;
using task45.ChainResponsobilityPattern;

class Component1 : BaseComponent
    {
        public void DoA()
        {
            Console.WriteLine("Component 1 does A.");

            this._mediator.Notify(this, "A");
        }

        public void DoB()
        {
            Console.WriteLine("Component 1 does B.");

            this._mediator.Notify(this, "B");
        }
    }

    class Component2 : BaseComponent
    {
        public void DoC()
        {
            Console.WriteLine("Component 2 does C.");

            this._mediator.Notify(this, "C");
        }

        public void DoD()
        {
            Console.WriteLine("Component 2 does D.");

            this._mediator.Notify(this, "D");
        }
    }



    class Program
    {
        static void Main(string[] args)
        {
            // Mediator
            Component1 component1 = new Component1();
            Component2 component2 = new Component2();
            new Mediator(component1, component2);

            Console.WriteLine("Client triggets operation A.");
            component1.DoA();

            Console.WriteLine();

            Console.WriteLine("Client triggers operation D.");
            component2.DoD();

            // Object pool
            BaseObjectPool pool = new ConcreteObjectPool();

            var firstStudent = pool.GetStudent();
            firstStudent.FirstName = "Bob";
            firstStudent.LastName = "Marley";
            firstStudent.BirthDay = new DateTime(1948, 11, 6);
             Console.WriteLine(firstStudent.FirstName);

            Console.WriteLine($"Can I get one more student? {pool.TryGetStudnet()}");

            var secondStudent = pool.GetStudent();
            secondStudent.FirstName = "Abraam";
            secondStudent.LastName = "Lincloln";
            secondStudent.BirthDay = new DateTime(1948, 11, 6);

            Console.WriteLine($"Can I get one more student? {pool.TryGetStudnet()}");

            var thirdStudent = pool.GetStudent();
            thirdStudent.FirstName = "Alice";
            thirdStudent.LastName = "Lavinton";
            thirdStudent.BirthDay = new DateTime(1968, 11, 6);


            Console.WriteLine($"Can I get one more student? {pool.TryGetStudnet()}");
            pool.ReturnStudent(ref firstStudent);
            Console.WriteLine($"Can I get one more student? {pool.TryGetStudnet()}");
            Console.WriteLine(firstStudent.FirstName);
            
            // Chain responsibility
            var plus = new PlusOperation();
            var minus = new MinusOperation();
            var multiply = new MultiplyOperation();
            var divide = new DivideOperation();

            plus.SetNext(minus).SetNext(multiply).SetNext(divide);

            string input;
            Console.WriteLine("Write first number, operation and second number. E.g. 2 + 5\nIf you want to exit, write \"exit\"");
            while (true)
            {
                input = Console.ReadLine();
                if (!input.Equals("exit"))
                {
                    try
                    {
                        Console.WriteLine(plus.Handle(input));
                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine(ex.Message);
                    }
                }
                else
                {
                    break;
                }
            }
        }
    }