package softuni.exam.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.Xmls.BorrowingRecordsRootDto;
import softuni.exam.models.dto.Xmls.BorrowingRecordsSeedDto;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.Genre;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.util.ValidationUtil;

import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

//import static javax.xml.bind.JAXBContext.newInstance;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {

    private static final String FILE_PATH = "src/main/resources/files/xml/borrowing-records.xml";
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final LibraryMemberRepository libraryMemberRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public BorrowingRecordsServiceImpl(@Valid BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importBorrowingRecords() throws JAXBException {
        StringBuilder sb = new StringBuilder();



        JAXBContext jaxbContext = JAXBContext.newInstance(BorrowingRecordsRootDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        BorrowingRecordsRootDto borrowingRecordsRootDto = (BorrowingRecordsRootDto) unmarshaller.unmarshal(new File(FILE_PATH));

        for (BorrowingRecordsSeedDto borrowingRecordsSeedDto : borrowingRecordsRootDto.getBorrowingRecordsSeedDtos() ) {

            Optional<Book> optionalBook = this.bookRepository.findByTitle(borrowingRecordsSeedDto.getBook().getTitle());
            Optional<LibraryMember> optionalLibraryMember = this.libraryMemberRepository.findById(borrowingRecordsSeedDto.getMember().getId());

            if(!this.validationUtil.isValid(borrowingRecordsSeedDto) || optionalBook.isEmpty() || optionalLibraryMember.isEmpty()
            || !this.validationUtil.isValid(optionalBook) || !this.validationUtil.isValid(optionalLibraryMember)) {
                sb.append("Invalid borrowing record\n");
                continue;
            }

            BorrowingRecord borrowingRecord = this.modelMapper.map(borrowingRecordsSeedDto, BorrowingRecord.class);
            borrowingRecord.setBook(optionalBook.get());
            borrowingRecord.setMember(optionalLibraryMember.get());

            if(borrowingRecord.getBorrowDate() == null || borrowingRecord.getRemarks() == null || borrowingRecord.getReturnDate() == null) {
                sb.append("Invalid borrowing record\n");
                continue;
            } else {
                this.borrowingRecordRepository.saveAndFlush(borrowingRecord);
                sb.append(String.format("Successfully imported borrowing record %s - %s\n", borrowingRecord.getBook().getTitle(), borrowingRecordsSeedDto.getBorrowDate()));
            }

        }

        return sb.toString();
    }

    @Override
    public String exportBorrowingRecords() {

        return this.borrowingRecordRepository.findAllBorrowingRecordsByBorrowDateBeforeAndBookGenreIsOrderByBorrowDateDesc(LocalDate.parse("2021-09-10"), Genre.SCIENCE_FICTION)
                .stream().map(b ->
                String.format("Book title: %s\n" +
                        "*Book author: %s\n" +
                        "**Date borrowed: %s\n" +
                        "***Borrowed by: %s %s\n",
                        b.getBook().getTitle(),b.getBook().getAuthor(), b.getBorrowDate(), b.getMember().getFirstName(), b.getMember().getLastName()))
                .collect(Collectors.joining());

    }
}
