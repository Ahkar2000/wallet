package com.ahkar.repository;

import com.ahkar.entity.Booking;
import com.ahkar.projection.BookingProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Query(value = "SELECT u.name AS userName, u.email, p.package_name AS packageName, " +
            "b.group_size AS groupSize, b.total_price AS totalPrice, b.schedule, c.category AS categoryName, b.created_at AS createdAt " +
            "FROM booking b " +
            "JOIN package p ON p.id = b.package_id " +
            "JOIN category c ON c.id = p.category_id " +
            "JOIN user u ON u.id = b.user_id " +
            "WHERE b.id = :id",nativeQuery = true)
    BookingProjection bookingForEmail(Long id);
}
