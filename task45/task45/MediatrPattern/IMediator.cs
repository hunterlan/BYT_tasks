    // The Mediator interface declares a method used by components to notify the
    // mediator about various events. The Mediator may react to these events and
    // pass the execution to other components.
    namespace MediatrPattern {
        public interface IMediator
        {
            void Notify(object sender, string ev);
        }
    }