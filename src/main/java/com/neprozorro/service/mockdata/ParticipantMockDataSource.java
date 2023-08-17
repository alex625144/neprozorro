package com.neprozorro.service.mockdata;

import com.neprozorro.model.Participant;
import com.neprozorro.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ParticipantMockDataSource {

    String source = "src/main/resources/mock/seller-buyer.txt";
    private final List<Participant> participants;
    private int counter = 0;
    private int edrpouCounter = 0;

    private final ParticipantRepository participantRepository;

    public ParticipantMockDataSource(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
        this.participants = generate();
    }

    private List<Participant> generate() {
        List<Participant> dataList = new ArrayList<>();
        Set<String> uniqueValue = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(source), StandardCharsets.UTF_8)))  {
            String line;
            while ((line = reader.readLine()) != null) {
                uniqueValue.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dataList = new ArrayList<>(uniqueValue.stream()
                .map(name -> {
                    var participant = new Participant();
                    participant.setEdrpou(String.valueOf(++edrpouCounter));
                    participant.setName(name);
                    return participant;
                })
                .toList());
        Collections.shuffle(dataList);

        return participantRepository.saveAll(dataList);
    }

    public Participant getNextParticipant() {
        if (counter == participants.size()) {
            Collections.shuffle(participants);
            counter = 0;
        }

        return participants.get(counter++);
    }
}
