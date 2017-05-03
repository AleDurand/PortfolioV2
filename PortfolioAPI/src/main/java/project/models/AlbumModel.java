package project.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import project.exceptions.CustomException;

@Entity
@Table(name = "album")
public class AlbumModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "type", nullable = false, columnDefinition = "int default 0")
	private AlbumType type;

	@Column(name = "path", nullable = false)
	private String path;

	@OneToMany
	@JoinColumn(name = "album_id")
	private List<PhotoModel> photos;

	@Column(name = "read_only", insertable = false, updatable = false)
	private boolean readOnly;

	@Column(name = "ts_create", insertable = false)
	private Date creationTime;

	@Column(name = "ts_update", insertable = false)
	private Date updateTime;

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

	@PrePersist
	protected void onCreate() {
		this.creationTime = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updateTime = new Date();
		if (this.readOnly) {
			throw new CustomException("exception.entity.read_only", null);
		}
	}
	
	@PreRemove
	protected void onRemove() {
		if (this.readOnly) {
			throw new CustomException("exception.entity.read_only", null);
		}
	}

}
