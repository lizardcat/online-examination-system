

package xss.it.backend.extension;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.community.dialect.SQLiteDialect;
import org.hibernate.dialect.DatabaseVersion;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolutionInfo;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.jdbc.spi.JdbcTypeRegistry;


public final class SqLiteDialectExtension extends SQLiteDialect {

    public SqLiteDialectExtension(DialectResolutionInfo info) {
        super(info);
    }

    public SqLiteDialectExtension() {
        this(DatabaseVersion.make(2, 0));
    }

    public SqLiteDialectExtension(DatabaseVersion version) {
        super(version);
    }

    @Override
    protected String columnType(int sqlTypeCode) {
        if (sqlTypeCode == SqlTypes.BLOB) {
            return "blob";
        }
        return super.columnType(sqlTypeCode);
    }

    @Override
    public void contributeTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        super.contributeTypes(typeContributions, serviceRegistry);
        JdbcTypeRegistry jdbcTypeRegistry = typeContributions.getTypeConfiguration().getJdbcTypeRegistry();
        jdbcTypeRegistry.addDescriptor(SqlTypes.BLOB, new BlobJdbcTypeExtension());
    }
}
