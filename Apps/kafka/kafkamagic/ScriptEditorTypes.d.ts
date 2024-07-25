declare var Magic: KafkaMagic.Cluster;

declare namespace KafkaMagic {
    interface MessageContext {
        Message: any;
        Topic: string;
        Partition: number;
        Offset: number;
        Timestamp: Date;
        Headers: any;
        Key: number[];
    }

    interface Cluster {
        /**
         * @returns true if script runs in validation mode.
         */
        validatingOnly: boolean;

        /**
         * Creates a cluster configuration object `KafkaClusterConfiguration` by reading data from KafkaMagic configuration store (file or memory).
         * Sensitive info removed, can't be used for connecting to cluster.
         * @param clusterName string name of the cluster registered in KafkaMagic configuration store.
         * @returns `KafkaClusterConfiguration` object retrieved from the configuration store.
         */
        getClusterConfig(clusterName: string): KafkaClusterConfiguration;

        /**
         * Helper method to make cluster configuration object `KafkaClusterConfiguration` with `bootstrapServers` property set.
         * You can set other configuration parameters and use this object in other commands in place of cluster name.
         * @param bootstrapServers A list of brokers as a CSV list of `host` or `host:port` values. @example `'localhost:9092'`
         * @returns `KafkaClusterConfiguration` object with `bootstrapServers` property set.
         */
        makeClusterConfig(bootstrapServers: string): KafkaClusterConfiguration;

        /**
         * Retrieves brokers, topics metadata for all topics in the cluster.
         * @param cluster string name of the cluster registered in KafkaMagic configuration store, or `KafkaClusterConfiguration` object defining all connection parameters for the cluster.
         * @returns `Metadata` object
         */
        getClusterMetadata(cluster: string | KafkaClusterConfiguration): Metadata;

        /**
         * Makes `Topic` object representing existing topic, to be used in searching and publishing operations.
         * @param cluster string name of the cluster registered in KafkaMagic configuration store, or `KafkaClusterConfiguration` object defining all connection parameters for the cluster.
         * @param topicName Name of the topic.
         * @returns `Topic` object
         */
        getTopic(cluster: string | KafkaClusterConfiguration, topicName: string): Topic;

        /**
         * Creates new topic in the cluster, returns `Topic` object representing created topic, to be used in searching and publishing operations.
         * @param cluster string name of the cluster registered in KafkaMagic configuration store, or `KafkaClusterConfiguration` object defining all connection parameters for the cluster.
         * @param topicName Name of the topic.
         * @param partitions Number of partitions for this topic, optional, defaults to 1
         * @param replicationFactor Number of replicas for this topic, optional, defaults to 1
         * @param avroSchema JSON string or object representation of Avro schema, defaults to `null`
         * @returns `Topic` object
         */
        createTopic(cluster: string | KafkaClusterConfiguration,
            topicName: string,
            partitions?: number,
            replicationFactor?: number,
            avroSchema?: any): Topic;

        /**
         * Retrieves Avro schema object from an Avro schema registry.
         * @param config string name of the cluster registered in KafkaMagic configuration store, 
         * or `SchemaRegistryConfiguration` object defining source Avro schema registry connection.
         * @param topicName Subject the schema is registered against.
         * @param version Optional version number of the schema, if not provided - gets latest version
         * @returns object representation of the schema
         */
        getAvroSchema(config: string | SchemaRegistryConfiguration, topicName: string, version?: number): any;

        /**
         * Registers Avro schema in the Avro schema registry.
         * @param config String name of the cluster registered in KafkaMagic configuration store,
         * or `SchemaRegistryConfiguration` object defining target Avro schema registry connection.
         * @param topicName Subject the schema should be registered against.
         * @param schema JSON string or object representation of Avro schema
         */
        registerAvroSchema(config: string | SchemaRegistryConfiguration, topicName: string, schema: string): void;

        /**
         * Creates new topic in the cluster copying number of partitions and Avro schema from a source topic, optionally from a different cluster.
         * @param cluster string name of the target cluster where new topic will be created, or `KafkaClusterConfiguration` object for the target cluster.
         * @param newTopicName Name of the new topic.
         * @param sourceTopic `Topic` object containing topicName and cluster defining source topic to be cloned.
         * @param partitions Number of partitions for this topic, optional, if provided overrides Number of partitions in the source topic.
         * @param replicationFactor Number of replicas for this topic, optional, default = 1.
         * @returns `Topic` object representing created topic.
         */
        createTopicClone(cluster: string | KafkaClusterConfiguration,
            newTopicName: string,
            sourceTopic: string,
            partitions?: number,
            replicationFactor?: number): Topic;

        /**
         * Adds arbitrary data to JSON array displayed in results window
         * @param data Any object to be displayed in the results window
         */
        reportProgress(data: any): void;

        /**
         * Delay execution
         * @param time Number of milliseconds
         */
        delay(time: number): void;
    }

    /**
     * `Topic` object representing Topic configuration and related operations
     */
    interface Topic {

        /**
         * Topic name 
         */
        topicName: string;

        /**
         * Name of the cluster registered in KafkaMagic configuration store,
         * or `KafkaClusterConfiguration` object defining all connection parameters for the cluster
         */
        cluster: string | KafkaClusterConfiguration;

        /**
         * Get topic metadata
         * @returns `TopicMetadata` object
         */
        getMetadata(): TopicMetadata;

        /**
         * Publish single message without providing a key or headers. 
         * If Avro serialization is requested, the message properties must conform to the schema.
         * @param message object content of the message
         * @param isAvro boolean true if message should be Avro serialized
         * @param options PublishingOptions object: PartitionId, CompressionType, CompressionLevel
         */
        publishMessage(message: any, isAvro: boolean, options?: PublishingOptions): void;

        /**
         * Publish single message with optional key, headers, and partition Id in a Context object.
         * If Avro serialization is requested, the message must conform to the schema.
         * @param message `MessageContext` object containing message, key, headers, and partitionId
         * @param isAvro boolean true if message should be Avro serialized
         * @param options PublishingOptions object: PartitionId, CompressionType, CompressionLevel
         */
        publishMessageContext(message: MessageContext, isAvro: boolean, options?: PublishingOptions): void;

        /**
         * Publish multiple messages without providing keys or headers.
         * If Avro serialization is requested, the message must conform to the schema.
         * @param messages array of objects
         * @param isAvro boolean true if message should be Avro serialized
         * @param options PublishingOptions object: PartitionId, CompressionType, CompressionLevel
         */
        publishMessageArray(messages: any[], isAvro: boolean, options?: PublishingOptions): void;

        /**
         * Publish multiple messages, each with a key, headers, and partition Id in a Context object.
         * If Avro serialization is requested, the message must conform to the schema.
         * @param messageContextArray Array of `MessageContext` objects, each containing message, key and headers
         * @param isAvro boolean true if message should be Avro serialized
         * @param options PublishingOptions object: PartitionId, CompressionType, CompressionLevel
         */
        publishMessageContextArray(messageContextArray: MessageContext[], isAvro: boolean, options?: PublishingOptions): void;

        /**
         * Search for messages in the topic
         * @param isAvro true if topic message is Avro serialized.
         * @param maxResults Maximum number of messages to return.
         * @param filter Callback function accepting `MessageContext` object and returning boolean value.
         * @param minTimestamp Date, string, or number - Minimum message timestamp to limit search scope to limit search scope by time.
         * @param maxTimestamp Date, string, or number - Maximum message timestamp to limit search scope to limit search scope by time.
         * @param partitionOffsets Array of `PartitionOffset` objects to limit search scope by partitions and offsets.
         * @returns Array of `MessageContext` objects.
         */
        search(isAvro: boolean, maxResults: number, filter?: SearchFilterFunction, minTimestamp?: Date, maxTimestamp?: Date,
            partitionOffsets?: PartitionOffset[]): MessageContext[];

        /**
         * Get topic statistics: min/max offsets and timestamps for each partition
         * @returns Array of `PartitionStats` objects
         */
        getStats(): PartitionStats[];

        /**
         * Delete topic and data
         */
        delete(): void;
    }

    type SearchFilterFunction = (Context: MessageContext) => boolean;

    interface PublishingOptions {
        PartitionId: number;
        CompressionType: string;
        CompressionLevel: number;
    }

    interface PartitionOffset {
        PartitionId: number;
        MinOffset: number;
        MaxOffset: number;
    }

    interface Metadata {
        Brokers: BrokerMetadata[];
        Topics: TopicMetadata[];
        OriginatingBrokerId: number;
        OriginatingBrokerName: string;
    }

    interface BrokerMetadata {
        BrokerId: number;
        Host: string;
        Port: number;
    }

    interface TopicMetadata {
        Topic: string;
        Partitions: PartitionMetadata[];
        Error: MetadataError;
    }

    interface PartitionStats {
        PartitionId: number;
        MinOffset: number;
        MinTimestamp: Date;
        MaxOffset: number;
        MaxTimestamp: Date;
    }

    interface PartitionMetadata {
        PartitionId: number;
        Leader: number;
        Replicas: number[];
        InSyncReplicas: number[];
        Error: MetadataError;
    }

    interface MetadataError {
        ErrorCode: number;
        IsFatal: boolean;
        Reason: string;
    }

    interface SchemaRegistryConfiguration {
        SchemaRegistryBasicAuthCredentialsSource: string;
        SchemaRegistryUrl: string;
        SchemaRegistryRequestTimeoutMs: number;
        SchemaRegistryMaxCachedSchemas: number;
        SchemaRegistryBasicAuthUserInfo: string;
        AutoRegisterSchemas: boolean;
    }

    interface KafkaClusterConfiguration {
        ClusterId: string;
        ClusterName: string;
        SchemaRegistry: SchemaRegistryConfiguration;
        LogConnectionClose: boolean;
        InternalTerminationSignal: number;
        ApiVersionRequest: boolean;
        ApiVersionRequestTimeoutMs: number;
        ApiVersionFallbackMs: number;
        BrokerVersionFallback: string;
        SecurityProtocol: string;
        SslCipherSuites: string;
        SslCurvesList: string;
        SslSigalgsList: string;
        SslKeyLocation: string;
        SslKeyPassword: string;
        SslCertificateLocation: string;
        SslCaLocation: string;
        SslCrlLocation: string;
        SslKeystoreLocation: string;
        SslKeystorePassword: string;
        SaslKerberosServiceName: string;
        SaslKerberosPrincipal: string;
        SaslKerberosKinitCmd: string;
        SaslKerberosKeytab: string;
        SaslKerberosMinTimeBeforeRelogin: number;
        SaslUsername: string;
        LogThreadName: boolean;
        LogQueue: boolean;
        StatisticsIntervalMs: number;
        ReconnectBackoffMaxMs: number;
        SaslMechanism: string;
        Acks: string;
        ClientId: string;
        BootstrapServers: string;
        MessageMaxBytes: number;
        MessageCopyMaxBytes: number;
        ReceiveMessageMaxBytes: number;
        MaxInFlight: number;
        MetadataRequestTimeoutMs: number;
        TopicMetadataRefreshIntervalMs: number;
        MetadataMaxAgeMs: number;
        SaslPassword: string;
        TopicMetadataRefreshFastIntervalMs: number;
        TopicBlacklist: string;
        Debug: string;
        SocketTimeoutMs: number;
        SocketSendBufferBytes: number;
        SocketReceiveBufferBytes: number;
        SocketKeepaliveEnable: boolean;
        SocketNagleDisable: boolean;
        SocketMaxFails: number;
        BrokerAddressTtl: number;
        BrokerAddressFamily: string;
        ReconnectBackoffMs: number;
        TopicMetadataRefreshSparse: boolean;
        PluginLibraryPaths: string;
        FetchErrorBackoffMs: number;
        FetchMinBytes: number;
        FetchMaxBytes: number;
        MaxPartitionFetchBytes: number;
        FetchWaitMaxMs: number;
        QueuedMaxMessagesKbytes: number;
        QueuedMinMessages: number;
        EnableAutoOffsetStore: boolean;
        AutoCommitIntervalMs: number;
        MaxPollIntervalMs: number;
        EnablePartitionEof: boolean;
        CoordinatorQueryIntervalMs: number;
        GroupProtocolType: string;
        HeartbeatIntervalMs: number;
        SessionTimeoutMs: number;
        PartitionAssignmentStrategy: string;
        GroupId: string;
        AutoOffsetReset: string;
        ConsumeResultFields: string;
        EnableAutoCommit: boolean;
        CheckCrcs: boolean;
        ConsumeBufferLength: number;
    }
}