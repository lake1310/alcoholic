package sso.mac.alcoholic.comm.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAccount is a Querydsl query type for Account
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccount extends EntityPathBase<Account> {

    private static final long serialVersionUID = 759024480L;

    public static final QAccount account = new QAccount("account");

    public final sso.mac.alcoholic.comm.entity.base.QBaseEntity _super = new sso.mac.alcoholic.comm.entity.base.QBaseEntity(this);

    //inherited
    public final StringPath created = _super.created;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    //inherited
    public final StringPath updated = _super.updated;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    //inherited
    public final EnumPath<sso.mac.alcoholic.comm.entity.base.Used> used = _super.used;

    public final StringPath username = createString("username");

    public QAccount(String variable) {
        super(Account.class, forVariable(variable));
    }

    public QAccount(Path<? extends Account> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccount(PathMetadata metadata) {
        super(Account.class, metadata);
    }

}

