| Category        | REST                                       | MESSAGING                                                                        |
| --------------- | ------------------------------------------ | -------------------------------------------------------------------------------- |
| Advantages      | - Can be used anywhere                     | - Middleware provides a buffer to collect messages until the receiver pulls them |
|                 | - Suitable for synchronous                 | - Loose coupling                                                                 |
|                 |                                            | - Supports pub-sub                                                               |
|                 |                                            | - Enables broadcasting                                                           |
| Disadvantages   | - No buffer                                | - Middleware typically only used within an organization                          |
|                 | - Tight coupling                           | - Single point of failure                                                        |
| When to use     | - When synchronous communication is needed | - When asynchronous (fire and forget) communication is needed                    |
|                 | - For communication between organizations  | - When broadcasting messages is required                                         |
| When not to use |                                            | - For communication outside the organization                                     |
|                 |                                            | - For web clients                                                                |
