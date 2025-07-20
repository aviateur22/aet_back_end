package com.ctoutweb.aet.infra.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "memory_card_game_image")
public class MemoryCardGameImageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @CreationTimestamp
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  /**
   * Relation
   */
  @ManyToOne
  @JoinColumn(name = "image_id", nullable = false)
  private ImageEntity image;

  @ManyToOne
  @JoinColumn(name = "card_face_id", nullable = false)
  private MemoryCardGameImageFaceEntity memoryCardGameImageFace;

  @ManyToOne
  @JoinColumn(name = "card_family_id", nullable = true)
  private MemoryCardGameImageFamilyEntity memoryCardGameImageFamily;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(ZonedDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public ImageEntity getImage() {
    return image;
  }

  public void setImage(ImageEntity image) {
    this.image = image;
  }

  public MemoryCardGameImageFaceEntity getMemoryCardGameImageFace() {
    return memoryCardGameImageFace;
  }

  public void setMemoryCardGameImageFace(MemoryCardGameImageFaceEntity memoryCardGameImageFace) {
    this.memoryCardGameImageFace = memoryCardGameImageFace;
  }

  public MemoryCardGameImageFamilyEntity getMemoryCardGameImageFamily() {
    return memoryCardGameImageFamily;
  }

  public void setMemoryCardGameImageFamily(MemoryCardGameImageFamilyEntity memoryCardGameImageFamily) {
    this.memoryCardGameImageFamily = memoryCardGameImageFamily;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MemoryCardGameImageEntity that = (MemoryCardGameImageEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(image, that.image) && Objects.equals(memoryCardGameImageFace, that.memoryCardGameImageFace) && Objects.equals(memoryCardGameImageFamily, that.memoryCardGameImageFamily);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, updatedAt, image, memoryCardGameImageFace, memoryCardGameImageFamily);
  }

  @Override
  public String toString() {
    return "MemoryCardGameImageEntity{" +
            "id=" + id +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", image=" + image +
            ", memoryCardGameImageFace=" + memoryCardGameImageFace +
            ", memoryCardGameImageFamily=" + memoryCardGameImageFamily +
            '}';
  }
}
