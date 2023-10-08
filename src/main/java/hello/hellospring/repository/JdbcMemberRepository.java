package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class JdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;
    // DB와 연결하려면 DataSource가 필요함

    public JdbcMemberRepository(DataSource dataSource){
        // spring으로부터 datasource를 주입 받아야함
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";
        //save하기위한 sqp문

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;// 결과

        try{
            conn = getConnection(); // 연결을 하고
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // sql을 집어넣고
            // RETRUN_GENERATED_KEYS는 DG에 insert할때 id값을 얻어옴


            pstmt.setString(1, member.getName());
            // paramaterindex : 1번은 ?와 매치됨 ?에 member.getName으로 가져온값을 넣음

            pstmt.executeUpdate();
            // DB에 실제 쿼리를 보냄


            rs = pstmt.getGeneratedKeys();
            // DB가 생성한 키를 rs에 반환
            // key값을 DB와 매칭해서 값을 반환해줌
            // ex) 1이면 Id가1 인값을 반환

            if(rs.next()){
                // rs가 값을 가지고 있으면
                member.setId(rs.getLong(1));
            }else{
                throw new SQLException("id 조회 실패");
            }
            return member;
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            // 사용한 자원들을 릴리즈 -> 연결 끊기
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql ="select * from member where id = ?";

        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,id);

            rs = pstmt.executeQuery();
            // 조회는 executeQuery를 사용


            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }else{
                return Optional.empty();
            }
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            close(conn, pstmt,rs);
        }

    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection conn =null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;

        try{
            conn =getConnection();
            pstmt =  conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();

            while(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }

            return members;
        }catch(Exception e){
            throw  new IllegalStateException(e);
        }finally{
            close(conn, pstmt,rs);
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name =?";

        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs =null;

        try{
            conn = getConnection();
            pstmt =  conn.prepareStatement(sql);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally{
            close(conn, pstmt, rs);
        }

    }

    private Connection getConnection(){
        // datasource와 connection을 맺음
        return DataSourceUtils.getConnection(dataSource);
        // 스프링을 통해서 DB와 연결하려면
        // DataSourceUtils를 통해서 Connection해야됨
        // DataSourceUtils는 database connection을 똑같게 유지해줌
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
        // 연결을 close
        try{
            if(rs !=null){
                // 결과 값을 담고있는 객체를 먼저 close
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        try{
            if(pstmt !=null){
                pstmt.close();
            }
            }catch (SQLException e){
                e.printStackTrace();
        }

        try{
            if(conn !=null){
                close(conn);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException{
        //conn에서 한해서 연걸을 close
        DataSourceUtils.releaseConnection(conn, dataSource);
        // 연결을 끊을때에도
        // DataSourceUtils를 사용해서 release를 해주어야함

    }


}
