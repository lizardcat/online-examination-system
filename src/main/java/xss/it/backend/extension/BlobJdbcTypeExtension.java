

package xss.it.backend.extension;

import org.hibernate.type.descriptor.ValueBinder;
import org.hibernate.type.descriptor.ValueExtractor;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.descriptor.jdbc.BasicBinder;
import org.hibernate.type.descriptor.jdbc.BasicExtractor;
import org.hibernate.type.descriptor.jdbc.JdbcType;

import java.sql.*;


public final class BlobJdbcTypeExtension implements JdbcType {
    @Override
    public int getJdbcTypeCode() {
        return Types.BLOB;
    }

    @Override
    public String getFriendlyName() {
        return "BLOB";
    }

    @Override
    public Class<?> getPreferredJavaTypeClass(WrapperOptions options) {
        return byte[].class;
    }

    @Override
    public <X> ValueBinder<X> getBinder(JavaType<X> javaType) {
        return new BasicBinder<>(javaType, this) {
            @Override
            protected void doBind(PreparedStatement st, X value, int index, WrapperOptions options) throws SQLException {
                st.setBytes(index, javaType.unwrap(value, byte[].class, options));
            }

            @Override
            protected void doBind(CallableStatement st, X value, String name, WrapperOptions options) throws SQLException {
                st.setBytes(name, javaType.unwrap(value, byte[].class, options));
            }
        };
    }

    @Override
    public <X> ValueExtractor<X> getExtractor(JavaType<X> javaType) {
        return new BasicExtractor<X>(javaType, this) {
            @Override
            protected X doExtract(ResultSet rs, int paramIndex, WrapperOptions options) throws SQLException {
                return javaType.wrap(rs.getBytes(paramIndex), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
                return javaType.wrap(statement.getBytes(index), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, String name, WrapperOptions options) throws SQLException {
                return javaType.wrap(statement.getBytes(name), options);
            }
        };
    }
}