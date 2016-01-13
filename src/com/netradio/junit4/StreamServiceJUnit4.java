package com.netradio.junit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.netradio.entity.Stream;
import com.netradio.entity.StreamView;
import com.netradio.service.StreamService;

public class StreamServiceJUnit4 extends AbstractServiceJUnit4 {

    private String prefix;

    @Autowired
    private StreamService srv;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Getting streams with expectation not null
     * */
    @Test
    public void getStreams() {
        List<Stream> streams = srv.getStreams();
        assertNotNull(streams);
    }

    /**
     * Getting stream with expectation not null
     */
    @Test
    public void getStreamForNotNull() {
        Stream stream = srv.getStream(0L);
        assertNotNull(stream);
    }

    /**
     * Getting streams with expectation null
     * */
    @Test
    public void getStreamForNull() {
        Stream stream = srv.getStream(-1L);
        assertNull(stream);
    }

    /**
     * Getting streams with null id
     * */
    @Test
    public void getStream() {
        Stream stream = srv.getStream(null);
        assertNull(stream);
    }

    /**
     * Saving correct stream without expectation exception
     * */
    @Test
    public void saveStreamForCorrect() {
        Stream stream = new Stream();
        stream.setFlow("JUnit4Test");
        stream.setLink("http://TestLink");
        stream.setCodecId(1L);
        stream.setBitrade(new BigDecimal(128));
        stream.setIdCreator(0L);
        byte[] content = {};
        srv.saveStream(stream, new MockMultipartFile("testFile", content),
                new Long[] {});
    }

    /**
     * Saving stream with incorrect id without expectation exception
     * */
    @Test
    public void saveStreamForIncorrectId() {
        Stream stream = new Stream();
        stream.setId(-1L);
        stream.setFlow("JUnit4Test");
        stream.setLink("http://TestLink");
        stream.setCodecId(1L);
        stream.setIdCreator(0L);
        stream.setBitrade(new BigDecimal(128));
        byte[] content = {};
        srv.saveStream(stream, new MockMultipartFile("testFile", content),
                new Long[] {});
    }

    /**
     * Saving stream with incorrect codec id with expectation exception
     * */
    @Test(expected = DataIntegrityViolationException.class)
    public void saveStreamForIncorrectIdCodec() {
        Stream stream = new Stream();
        stream.setFlow("JUnit4Test");
        stream.setLink("http://TestLink");
        stream.setCodecId(-1L);
        stream.setBitrade(new BigDecimal(128));
        stream.setIdCreator(0L);
        byte[] content = {};
        srv.saveStream(stream, new MockMultipartFile("testFile", content),
                new Long[] {});
    }

    /**
     * Saving stream with incorrect genres ids with expectation exception
     * */
    @Test(expected = DataIntegrityViolationException.class)
    public void saveStreamForIncorectGeneresIds() {
        Stream stream = new Stream();
        stream.setFlow("JUnit4Test");
        stream.setLink("http://TestLink");
        stream.setCodecId(1L);
        stream.setBitrade(new BigDecimal(128));
        stream.setIdCreator(0L);
        byte[] content = {};
        srv.saveStream(stream, new MockMultipartFile("testFile", content),
                new Long[] { -1L });
    }

    /**
     * Saving stream with incorrect creator id with expectation exception
     * */
    @Test(expected = DataIntegrityViolationException.class)
    public void saveStreamForIncorectCreatorId() {
        Stream stream = new Stream();
        stream.setFlow("JUnit4Test");
        stream.setLink("http://TestLink");
        stream.setCodecId(1L);
        stream.setBitrade(new BigDecimal(128));
        stream.setIdCreator(-1L);
        byte[] content = {};
        srv.saveStream(stream, new MockMultipartFile("testFile", content),
                new Long[] {});
    }

    /**
     * Saving stream with file size not equals 0
     * */
    @Test
    public void saveStreamForFileSizeNotEqualsZero() {
        Stream stream = new Stream();
        stream.setFlow("JUnit4Test");
        stream.setLink("http://TestLink");
        stream.setCodecId(1L);
        stream.setBitrade(new BigDecimal(128));
        stream.setIdCreator(0L);
        byte[] content = { 1, 2, 3, 4, 5, 6, 7, 8 };
        MultipartFile file = new MockMultipartFile("testFile", content);
        srv.saveStream(stream, file, new Long[] {});
    }

    /**
     * Saving stream with incorrect file
     * */
    @Test
    public void saveStreamForIncorrectFile() {
        Stream stream = new Stream();
        stream.setFlow("JUnit4Test");
        stream.setLink("http://TestLink");
        stream.setCodecId(1L);
        stream.setBitrade(new BigDecimal(128));
        stream.setIdCreator(0L);
        byte[] content = null;
        MultipartFile file = new MockMultipartFile("3", content);
        srv.saveStream(stream, file, new Long[] {});
    }

    /**
     * Saving stream with incorrect genres ids
     * */
    @Test
    public void saveStreamForGidsNull() {
        Stream stream = new Stream();
        stream.setFlow("JUnit4Test");
        stream.setLink("http://TestLink");
        stream.setCodecId(1L);
        stream.setBitrade(new BigDecimal(128));
        stream.setIdCreator(0L);
        byte[] content = null;
        MultipartFile file = new MockMultipartFile("3", content);
        srv.saveStream(stream, file, null);
    }

    /**
     * Deleting stream with correct id without expectation exception
     * */
    @Test
    public void deleteStreamForCorrectId() {
        srv.deleteStream(new Long(0));
    }

    /**
     * Deleting stream with incorrect id without expectation exception
     * */
    @Test
    public void deleteStreamForIncorrectId() {
        srv.deleteStream(-1L);
    }

    /**
     * Getting stream image with correct id without expectation exception You
     * need create 0.dat in C:\repofiles
     * */
    @Test
    public void getImageForCorrectId() {
        InputStream is = srv.getImage(0L);
        assertNotNull(is);
    }

    /**
     * Getting stream image with correct id without expectation exception You
     * deleted 1.dat in C:\repofiles, if it exists
     * */
    @Test
    public void getImageForCorrectId2() {
        InputStream is = srv.getImage(1L);
        assertNull(is);
    }

    /**
     * Getting stream image with incorrect id without expectation exception
     * */
    @Test
    public void getImageForIncorrectId() {
        InputStream is = srv.getImage(-1L);
        assertNull(is);
    }

    /**
     * Getting count favorite streams with by genres name
     * */
    @Test
    public void countFavoriteStreamsViewsForStreamsNames() {
        Integer count = srv.countStreamViews("Tele", "", true, true, 1L);
        assertNotEquals(0, count.intValue());
    }

    /**
     * Getting count favorite streams with by stream name
     * */
    @Test
    public void countFavoriteStreamsViewsForGenresNames() {
        Integer count = srv.countStreamViews("70's", "genres", true, true, 1L);
        assertNotEquals(0, count.intValue());
    }

    /**
     * Getting count streams by name streams
     * */
    @Test
    public void countStreamsViewsForNameStreams() {
        Integer count = srv.countStreamViews("A", "", true, false, 1L);
        assertNotEquals(0, count.intValue());
    }

    /**
     * Getting count streams by name genres
     * */
    @Test
    public void countStreamsViewsForNameGenres() {
        Integer count = srv.countStreamViews("70's", "genres", true, false, 0L);
        assertNotEquals(0, count.intValue());
    }

    /**
     * Getting streamsViews with correct page number
     * */
    @Test
    public void getStreamsViewsForPageNum() {
        List<StreamView> streamViews = srv.getStreamViews(1, true);
        assertNotNull(streamViews);
    }

    /**
     * Getting streamsViews with incorrect page number
     * */
    @Test(expected = DataIntegrityViolationException.class)
    public void getStreamsViewsForPageNum2() {
        List<StreamView> streamViews = srv.getStreamViews(-1, true);
        assertNull(streamViews);
    }

    /**
     * Getting streamsViews favorites by streams names (expectation not null)
     * */
    @Test
    public void getStreamsViewsF() {
        List<StreamView> streamViews = srv.getStreamViewsF("Tele", 1, "", true,
                1L);
        assertNotNull(streamViews);
    }

    /**
     * Getting streamsViews for null name
     * */
    @Test
    public void getStreamsViewsForNullName() {
        List<StreamView> streamViews = srv.getStreamViews(null, 1, "", true);
        assertNotNull(streamViews);
    }

    /**
     * Getting streamsViews favorites by streams names (expectation size>0)
     * */
    @Test
    public void getStreamsViewsF2() {
        List<StreamView> streamViews = srv
                .getStreamViewsF("s", 0, "", true, 1L);
        assertNotEquals(streamViews.toString(), 0, streamViews.size());
    }

    /**
     * Getting streamsViews favorites by streams names (expectation size==0)
     * */
    @Test
    public void getStreamsViewsF3() {
        List<StreamView> streamViews = srv.getStreamViewsF("NULL_RADIO", 1, "",
                true, 1L);
        assertEquals(0, streamViews.size());
    }

    /**
     * Getting streamsViews favorites by genres names (expectation not null)
     * */
    @Test
    public void getStreamsViewsF4() {
        List<StreamView> streamViews = srv.getStreamViewsF("70's", 1, "genres",
                true, 1L);
        assertNotNull(streamViews);
    }

    /**
     * Getting streamsViews favorites by genres names (expectation size>0)
     * */
    @Test
    public void getStreamsViewsF5() {
        List<StreamView> streamViews = srv.getStreamViewsF("70's", 0, "genres",
                true, 1L);
        assertNotEquals(streamViews.toString(), 0, streamViews.size());
    }

    /**
     * Getting streamsViews favorites by genres names (expectation size==0)
     * */
    @Test
    public void getStreamsViewsF6() {
        List<StreamView> streamViews = srv.getStreamViewsF("NULL_GENRE", 1,
                "genres", true, 1L);
        assertEquals(0, streamViews.size());
    }

    /**
     * Getting streamsViews by streams names (expectation not null)
     * */
    @Test
    public void getStreamsViews() {
        List<StreamView> streamViews = srv.getStreamViews("A", 1, "", true);
        assertNotNull(streamViews);
    }

    /**
     * Getting streamsViews by streams names (expectation size>0)
     * */
    @Test
    public void getStreamsViews2() {
        List<StreamView> streamViews = srv.getStreamViews("A", 0, "", true);
        assertNotEquals(streamViews.toString(), 0, streamViews.size());
    }

    /**
     * Getting streamsViews by streams names (expectation size==0)
     * */
    @Test
    public void getStreamsViews3() {
        List<StreamView> streamViews = srv.getStreamViews("NULL_RADIO", 1, "",
                true);
        assertEquals(0, streamViews.size());
    }

    /**
     * Getting streamsViews by genres names (expectation not null)
     * */
    @Test
    public void getStreamsViews4() {
        List<StreamView> streamViews = srv.getStreamViews("A", 1, "genres",
                true);
        assertNotNull(streamViews);
    }

    /**
     * Getting streamsViews by genres names (expectation size>0)
     * */
    @Test
    public void getStreamsViews5() {
        List<StreamView> streamViews = srv.getStreamViews("70's", 0, "genres",
                true);
        assertNotEquals(0, streamViews.size());
    }

    /**
     * Getting streamsViews by genres names (expectation size==0)
     * */
    @Test
    public void getStreamsViews6() {
        List<StreamView> streamViews = srv.getStreamViews("NULL_GENRE", 1,
                "genres", true);
        assertEquals(0, streamViews.size());
    }

    /**
     * Getting streamsViews by genres ids (expectation not null)
     * */
    @Test
    public void getFlowsByGenres() {
        List<StreamView> streamViews = srv.getFlowsByGenres(new Long[] { 1L,
                2L, 3L });
        assertNotNull(streamViews);
    }

    /**
     * Getting streamsViews by genres ids (expectation null)
     * */
    @Test
    public void getFlowsByGenres2() {
        List<StreamView> streamViews = srv.getFlowsByGenres(new Long[] { -1L });
        assertNotNull(streamViews);
    }

    /**
     * Getting streamsViews by genres ids for empty gids array (expectation
     * exception)
     * */
    @Test(expected = BadSqlGrammarException.class)
    public void getFlowsByGenres3() {
        List<StreamView> streamViews = srv.getFlowsByGenres(new Long[] {});
        assertNotNull(streamViews);
    }

    /**
     * Setting stream actual flag by id
     * */
    @Test
    public void setActual() {
        srv.setActual(0L, false);
    }

    /**
     * Setting stream actual flag by incorrect id
     * */
    @Test
    public void setActual2() {
        srv.setActual(-1L, false);
    }

    /**
     * Setting stream actual flag by null id
     * */
    @Test
    public void setActual3() {
        srv.setActual(null, false);
    }

    /**
     * Setting stream favorite
     * */
    @Test
    public void setFavorite() {
        srv.setFavorite(1L, 1L);
    }

    /**
     * Setting stream favorite with incorrect user id
     * */
    @Test(expected = DataIntegrityViolationException.class)
    public void setFavorite2() {
        srv.setFavorite(-1L, 0L);
    }

    /**
     * Setting stream favorite with incorrect stream id
     * */
    @Test
    public void setFavorite3() {
        srv.setFavorite(0L, -1L);
    }

    /**
     * Setting stream favorite with null parameters
     * */
    @Test
    public void setFavorite4() {
        srv.setFavorite(0L, null);
    }

    /**
     * Setting stream favorite with null user id
     * */
    @Test
    public void setFavorite5() {
        srv.setFavorite(null, 1L);
    }

    /**
     * Deleting stream favorite
     * */
    @Test
    public void delFavorite() {
        srv.delFavorite(1L, 1L);
    }

    /**
     * Deleting stream favorite with incorrect user id
     * */
    @Test
    public void delFavorite2() {
        srv.delFavorite(-1L, 1L);
    }

    /**
     * Deleting stream favorite with incorrect stream id
     * */
    @Test
    public void delFavorite3() {
        srv.delFavorite(1L, -1L);
    }

    /**
     * Deleting stream favorite with null user id
     * */
    @Test
    public void delFavorite4() {
        srv.delFavorite(null, -1L);
    }

    /**
     * Deleting stream favorite with null stream id
     * */
    @Test
    public void delFavorite5() {
        srv.delFavorite(1L, null);
    }

    /**
     * Getting streams favorites ids (expectation not null)
     * */
    @Test
    public void getFavorites() {
        List<Long> ids = srv.getFavorites(1L);
        assertNotNull(ids.size());
    }

    /**
     * Getting streams favorites ids
     * */
    @Test
    public void getFavorites2() {
        List<Long> ids = srv.getFavorites(1L);
        assertNotEquals(0, ids.size());
    }

    /**
     * Getting streams favorites ids by user without favorites streams
     * */
    @Test
    public void getFavorites3() {
        List<Long> ids = srv.getFavorites(0L);
        assertEquals(0, ids.size());
    }

    /**
     * Getting stream info (expectation not null)
     * */
    @Test
    public void getStreamInfo() {
        StreamView stream = srv.getStreamInfo(0L);
        assertNotNull(stream);
    }

    /**
     * Getting stream info (expectation not null)
     * */
    @Test
    public void getStreamInfo2() {
        StreamView stream = srv.getStreamInfo(-1L);
        assertNull(stream);
    }
}
