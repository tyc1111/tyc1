package com.example.bysj.service;

import javax.swing.tree.TreeNode;
import java.util.List;

public interface TycService {
        void define(String[] lists);

        void download();

        void createtable(String sql,String tableName);

        List<TreeNode> createTree();
}
