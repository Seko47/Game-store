package com.example.projekt.repositories;

import com.example.projekt.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository< Status, Integer >
{
}
