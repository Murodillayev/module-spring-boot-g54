package uz.pdp.todo.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uz.pdp.todo.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoDaoImpl implements TodoDao {

    private final JdbcTemplate jdbcTemplate;

    public TodoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Todo> findAll() {
        String sql = "select * from todo order by id desc";
        return jdbcTemplate.query(sql, new TodoRowMapper());
    }

    @Override
    public Optional<Todo> findById(String id) {
        String sql = "select * from todo where id = ?";

//        jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Todo.class), id);
        jdbcTemplate.queryForObject(sql, new TodoRowMapper(), id);
        return Optional.empty();
    }

    @Override
    public Todo save(Todo todo) {
        deleteById(todo.getId());
        String sql = "insert into todo(id,title,description,completed) values(?,?,?,?)";
        int update = jdbcTemplate.update(sql, todo.getId(), todo.getTitle(), todo.getDescription(), todo.isCompleted());
        return todo;
    }

    @Override
    public void deleteById(String id) {
        String sql = "delete from todo where id = ?";
        jdbcTemplate.update(sql, id);
    }

    static class TodoRowMapper implements RowMapper<Todo> {
        @Override
        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Todo todo = new Todo();
            todo.setId(rs.getString("id"));
            todo.setTitle(rs.getString("title"));
            todo.setCompleted(rs.getBoolean("completed"));
            todo.setDescription(rs.getString("description"));
            return todo;
        }
    }
}


