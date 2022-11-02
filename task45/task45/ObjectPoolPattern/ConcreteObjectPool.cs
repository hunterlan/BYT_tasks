namespace ObjectPoolPattern {
    public class ConcreteObjectPool : BaseObjectPool
    {
        public ConcreteObjectPool() : base()
        {
            for (int i = 0; i < this.maxSizePool; i++) {
                _studentsPool.Enqueue(new Student());
            }
        }
        public override Student GetStudent()
        {
            if (this._studentsPool.Count == 0) throw new Exception("Pool is empty!");
            return this._studentsPool.Dequeue();
        }

        public override bool TryGetStudnet() {
            return this._studentsPool.Count > 0;
        }

        public override void ReturnStudent(ref Student student)
        {
            if (this._studentsPool.Count >= this.maxSizePool) throw new Exception("Pool is full!");
            student = new Student();
            this._studentsPool.Enqueue(student);
        }
    }
}