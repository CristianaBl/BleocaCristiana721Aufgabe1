package parser;

import model.Result;

import java.io.IOException;
import java.util.List;

public interface Parser {
    List<Result> parseLogs(String path) throws IOException;
}