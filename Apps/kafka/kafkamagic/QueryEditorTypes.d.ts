declare var Context: KafkaMagic.IMessageContext;

declare namespace KafkaMagic {
    interface IMessageContext {
        Message: any;
        Topic: string;
        Partition: number;
        Offset: number;
        Timestamp: Date;
        Headers: any;
        Key: number[];
    }
}