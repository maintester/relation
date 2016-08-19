package com.relation.interfaces;

import java.util.Set;

public interface IPersistance {

	Set<String> readNameFile(String filename);

	boolean writeResultFile(String filename, Set<String> values);

	Set<String> readTextFile(String filename);

	Set<String> readResultFile(String filename);

	Set<String> readUrls();

}
