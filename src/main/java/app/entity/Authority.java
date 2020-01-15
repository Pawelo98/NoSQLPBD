package app.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class Authority implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    AuthorityId id;
	
	public Authority() {
		
	}
	
	public Authority(User user, String role) {
		this.id = new AuthorityId(user, role);
	}

	public AuthorityId getId() {
		return id;
	}

	public void setId(AuthorityId id) {
		this.id = id;
	}
}

@Embeddable
class AuthorityId implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="Username")
	private User user;

	@Column(name="authority")
	private String authority;
	
	public AuthorityId() {
		
	}
	
	public AuthorityId(User us, String auth) {
		this.user = us;
		this.authority = auth;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorityId)) return false;
        AuthorityId that = (AuthorityId) o;
        return Objects.equals(getAuthority(), that.getAuthority()) &&
                Objects.equals(getUser(), that.getUser());
    }

	@Override
	public int hashCode() {
		return Objects.hash(getUser(), getAuthority());
	}
}
