package project.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "image")
@EntityListeners(AuditingEntityListener.class)
public class PhotoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "path", nullable = false)
	private String path;

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
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "album_id")
	private AlbumModel album;

	public PhotoModel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public AlbumModel getAlbum() {
		return album;
	}

	public void setAlbum(AlbumModel album) {
		this.album = album;
	}

}
