package com.cloud.kjetboy.server.sso.dao;

import com.cloud.kjetboy.server.sso.entity.Client;

import java.util.List;

/**
 * @author jet
 */
public interface ClientDao {

    Client createClient(Client client);

    Client updateClient(Client client);

    void deleteClient(Long clientId);

    Client findOne(Long clientId);

    List<Client> findAll();

    Client findByClientId(String clientId);

    Client findByClientSecret(String clientSecret);

}
