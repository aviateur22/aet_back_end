package com.ctoutweb.aet.infra.repository.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "game_image")
public class GameImageEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
 Long id;

  @CreationTimestamp
  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "image_id", nullable = false)
  private ImageEntity image;

  @ManyToOne
  @JoinColumn(name="game_id", nullable=false)
  private GameEntity game;

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

  public GameEntity getGame() {
    return game;
  }

  public void setGame(GameEntity game) {
    this.game = game;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GameImageEntity that = (GameImageEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(image, that.image) && Objects.equals(game, that.game);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, updatedAt, image, game);
  }

  @Override
  public String toString() {
    return "GameImageEntity{" +
            "id=" + id +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            ", image=" + image +
            ", game=" + game +
            '}';
  }
}
