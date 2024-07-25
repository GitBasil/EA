# NOTES FOR CLASS 2

### Orchestration vs Choreography

**Orchestration**: In this pattern, there is a central controller or orchestrator that coordinates and controls the interactions between different components or services. It typically involves a central point of control that dictates the flow of activities or processes.

**Choreography**: This pattern emphasizes decentralized coordination. Instead of a central orchestrator, each component or service knows how to interact with others. Interactions are based on events or messages, and each component reacts to events independently.

### Beans

Keep in mind that all Beans are singleton, thats why we don't add properties in it, because we need it to be stateless, there is a case where you can add prototype on the Bean to make it not singleton but it's not recommended to do that.

### AOP

Aspect-Oriented Programming (AOP) in Java is a way to handle common tasks, like logging or security checks, separately from the main code. It uses aspects to encapsulate these tasks, making the code cleaner and easier to manage.

For example, imagine you have a Java application with many methods that need logging. Instead of adding logging code to each method, you create a logging aspect. This aspect contains the logging instructions, and you apply it to the methods where logging is needed. This way, you keep your main code focused on its main job, while the logging aspect takes care of logging details.

This can be used only on Beans not on business classes
