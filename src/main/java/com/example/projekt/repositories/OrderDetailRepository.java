package com.example.projekt.repositories;

import com.example.projekt.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository< OrderDetail, Long >
{
}
