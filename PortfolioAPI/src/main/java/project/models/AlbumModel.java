package project.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "album")
@EntityListeners(AuditingEntityListener.class)
public class AlbumModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "type")
	private AlbumType type = AlbumType.UNKNOWN;;

	@Column(name = "path", nullable = false)
	private String path;

	@OneToMany
	@JoinColumn(name = "album_id")
	private List<PhotoModel> photos;

	@Column(name = "read_only")
	private boolean readOnly;
	
	@CreatedDate
	@Column(name = "ts_create", nullable = false)
	private Date creationTime;

	@LastModifiedDate
	@Column(name = "ts_update", nullable = false)
	private Date updateTime;

	@CreatedBy
	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@LastModifiedBy
	@Column(name = "updated_by", nullable = false)
	private String updatedBy;

	public AlbumModel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AlbumType getType() {
		return type;
	}

	public void setType(AlbumType type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<PhotoModel> getPhotos() {
		return photos;
	}

	public void setPhotos(List<PhotoModel> photos) {
		this.photos = photos;
	}

	public boolean isReadOnly() {
		return readOnly;
	}
	
	public Date getCreationTime() {
		return creationTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

}
