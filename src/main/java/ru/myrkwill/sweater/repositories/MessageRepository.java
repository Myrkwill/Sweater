package ru.myrkwill.sweater.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.myrkwill.sweater.entities.Message;

/**
 * @author Mark Nagibin
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
}
