namespace ObjectPoolPattern {
    public abstract class BaseObjectPool {
        protected Queue<Student> _studentsPool;

        protected readonly int maxSizePool = 3;

        public BaseObjectPool()
        {
            _studentsPool = new Queue<Student>(maxSizePool);
        }

        public abstract Student GetStudent();

        public abstract bool TryGetStudnet();

        public abstract void ReturnStudent(ref Student student);
    }
}